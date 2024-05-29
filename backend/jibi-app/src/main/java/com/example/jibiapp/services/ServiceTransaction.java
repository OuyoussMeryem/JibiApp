package com.example.jibiapp.services;

import com.example.jibiapp.models.*;
import com.example.jibiapp.repositories.ActionServiceRepo;
import com.example.jibiapp.repositories.CMIServiceRepo;
import com.example.jibiapp.repositories.TransactionRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceTransaction {

    private TransactionRepo transactionRepository;

    private CMIServiceRepo cmiServiceRepository; // Assuming you have a repository for CMIService

    @Autowired
    public ServiceTransaction(CMIServiceRepo cmiServiceRepo, TransactionRepo transactionRepository) {
        this.cmiServiceRepository = cmiServiceRepo;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    public void makePayment(Compte compte, ActionService actionService, Paiment paiment, CMIService cmiService) {
        try {
            // Log the start of the payment process
            System.out.println("Starting payment process...");
            System.out.println("Compte: " + compte);
            System.out.println("ActionService: " + actionService);
            System.out.println("Paiment: " + paiment);
            System.out.println("CMIService: " + cmiService);

            // Check if the account has sufficient balance
            if (!compte.hasSufficientBalance(paiment.getMontant())) {
                System.out.println("Insufficient balance in the account");
                throw new IllegalArgumentException("Insufficient balance in the account");
            }

            // Perform additional processing or validation with CMIService
            if (cmiService != null) {
                System.out.println("Validating transaction with CMIService...");
                boolean isValid = cmiService.validerTransaction(paiment);
                if (!isValid) {
                    System.out.println("Payment validation failed by CMIService");
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
            transaction.setCmiService(cmiService); // Set the associated CMIService
            transaction.mettreEnAttente(); // Set transaction status to "En Attente"
            System.out.println("Transaction created and set to 'En Attente' status");

            // Update the associated action service status
            actionService.payer(); // Set action service status to "Payée"
            System.out.println("ActionService status set to 'Payée'");

            // Update the transaction status
            transaction.valider(); // Set transaction status to "Validée"
            System.out.println("Transaction status updated to 'Validée'");

            // Debit the account balance
            if (!compte.debitAccount(paiment.getMontant())) {
                System.out.println("Failed to debit account");
                throw new IllegalStateException("Failed to debit account");
            }
            System.out.println("Account debited successfully");

            // Add the transaction to the list of transactions associated with the account
            compte.getTransactions().add(transaction);
            System.out.println("Transaction added to the account's transaction list");

            // Print transaction details
            transaction.printDetails();
            System.out.println("Transaction details printed");

            // Save the transaction to the repository
            transactionRepository.save(transaction);
            System.out.println("Transaction saved to the repository");

        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
            throw e; // Re-throw the exception to ensure transaction rollback and proper error reporting
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            throw new RuntimeException("An error occurred during the payment process", e);
        }
    }

}
