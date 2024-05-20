package com.example.jibiapp.services;

import com.example.jibiapp.models.*;
import com.example.jibiapp.repositories.ActionServiceRepo;
import com.example.jibiapp.repositories.CMIServiceRepo;
import com.example.jibiapp.repositories.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class ServiceTransaction {


    @Autowired
    private TransactionRepo transactionRepository;

    @Autowired
    private CMIServiceRepo cmiServiceRepository; // Assuming you have a repository for CMIService



    @Autowired
    public ServiceTransaction(CMIServiceRepo cmiServiceRepo) {
        this.cmiServiceRepository = cmiServiceRepo;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    public void makePayment(Compte compte, ActionService actionService, Paiment paiment, CMIService cmiService) {
        // Check if the account has sufficient balance
        if (!compte.hasSufficientBalance(paiment.getMontant())) {
            throw new IllegalArgumentException("Insufficient balance in the account");
        }

        // Perform additional processing or validation with CMIService
        if (cmiService != null) {
            boolean isValid = cmiService.validerTransaction(compte, actionService, paiment);
            if (!isValid) {
                throw new IllegalArgumentException("Payment validation failed by CMIService");
            }
        }

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setMontant(paiment.getMontant());
        transaction.setDate(new Date());
        transaction.setCompte(compte);
        transaction.setActionService(actionService);
        transaction.setPaiment(paiment);
        transaction.mettreEnAttente(); // Set transaction status to "En Attente"

        // Update the associated action service status
        actionService.payer(); // Set action service status to "Payée"

        // Update the transaction status
        transaction.valider(); // Set transaction status to "Validée"

        // Debit the account balance
        if (!compte.debitAccount(paiment.getMontant())) {
            throw new IllegalStateException("Failed to debit account");
        }

        // Add the transaction to the list of transactions associated with the account
        compte.getTransactions().add(transaction);

        // Print transaction details
        transaction.printDetails();
        transactionRepository.save(transaction); // Assuming transactionRepository is autowired

    }


}
