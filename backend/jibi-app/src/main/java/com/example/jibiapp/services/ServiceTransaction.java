package com.example.jibiapp.services;

import com.example.jibiapp.enums.EtatActionService;
import com.example.jibiapp.models.*;
import com.example.jibiapp.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
/*
@AllArgsConstructor
*/
public class ServiceTransaction {

    private TransactionRepo transactionRepo;

    private CMIServiceRepo cmiServiceRepo; // Assuming you have a repository for CMIService

    @Autowired
    private PaimentRepo paimentRepo;

    @Autowired
    private CompteRepo compteRepo;

    @Autowired
    private ActionServiceRepo actionServiceRepo;

    @Autowired
    private ServicesRepo servicesRepo;

    private static final Logger logger = LoggerFactory.getLogger(ServiceTransaction.class);



    @Autowired
    public ServiceTransaction(TransactionRepo transactionRepo, CMIServiceRepo cmiServiceRepo, PaimentRepo paimentRepo, CompteRepo compteRepo, ActionServiceRepo actionServiceRepo, ServicesRepo servicesRepo) {
        this.transactionRepo = transactionRepo;
        this.cmiServiceRepo = cmiServiceRepo;
        this.paimentRepo = paimentRepo;
        this.compteRepo = compteRepo;
        this.actionServiceRepo = actionServiceRepo;
        this.servicesRepo = servicesRepo;
    }


    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        logger.info("Saving transaction: {}", transaction);
        return transactionRepo.save(transaction);
    }



    @Transactional
    public Transaction createAndSaveTransaction(Long transferFrom, Long transferTo, Double transferAmount, Long cmiServiceId, Long serviceId, Long actionServiceId) {
        if (actionServiceId == null) {
            throw new IllegalArgumentException("ActionService ID must not be null");
        }

        // Retrieve the existing ActionService
        Optional<ActionService> optionalActionService = actionServiceRepo.findById(actionServiceId);
        ActionService actionService = optionalActionService.orElseThrow(() -> new RuntimeException("ActionService not found"));

        // Retrieve the existing CMIService
        Optional<CMIService> optionalCMIService = cmiServiceRepo.findById(cmiServiceId);
        CMIService cmiService = optionalCMIService.orElseThrow(() -> new RuntimeException("CMIService not found"));

        // Retrieve the existing Service
        Optional<Services> optionalServices = servicesRepo.findById(serviceId);
        Services service = optionalServices.orElseThrow(() -> new RuntimeException("Service not found"));

        // Retrieve source and destination accounts
        Optional<Compte> optionalTransferFromCompte = compteRepo.findById(transferFrom);
        Compte transferFromCompte = optionalTransferFromCompte.orElseThrow(() -> new RuntimeException("Transfer from Compte not found"));

        //REtrieve the client associated with the transfer from compte
        Client client = transferFromCompte.getClient();

        // Ensure transfer amount is sufficient
        if (transferAmount < actionService.getMontant()) {
            throw new IllegalArgumentException("Transfer amount is insufficient for the service");
        }

        // Create a new Transaction entity
        Transaction transaction = new Transaction();
        transaction.setDate(new Date());
        transaction.setMontant(transferAmount);
        transaction.setActionService(actionService); // Associate ActionService with Transaction

        // Save the transaction
        transaction = transactionRepo.save(transaction);

        // Perform debit and credit operations
        performDebitAndCredit(transferFrom, transferTo, transferAmount);

        // Modify ActionService status
        actionService.payer(); // Assuming this changes the status to "paid"
        actionServiceRepo.save(actionService); // Save the updated ActionService

        // Set the IDs for Compte and CMIService
        transaction.setCompte(transferFromCompte);
        transaction.setCmiService(cmiService);

        // Create and save Paiment entity
        Paiment paiment = new Paiment();
        paiment.setDatePaiment(LocalDateTime.now()); // Set the current date
        paiment.setMontant(transferAmount);
        paiment.setService(service); // Set the service
        paiment.setTransaction(transaction); // Set the transaction
        paiment.setClient(client);
        paiment = paimentRepo.save(paiment);

        // Set the Paiment ID in the Transaction entity
        transaction.setPaiment(paiment);

        // Set the status of the transaction
        transaction.valider(); // Assuming the transaction is valid after creation

        return transaction;
    }



    private void performDebitAndCredit(Long transferFrom, Long transferTo, Double transferAmount) {
        // Retrieve source and destination accounts
        Optional<Compte> optionalTransferFromCompte = compteRepo.findById(transferFrom);
        Optional<Compte> optionalTransferToCompte = compteRepo.findById(transferTo);
        Compte transferFromCompte = optionalTransferFromCompte.get();
        Compte transferToCompte = optionalTransferToCompte.get();

        // Check if the source and destination accounts are the same
        if (transferFrom.equals(transferTo)) {
            throw new RuntimeException("Source and destination accounts cannot be the same");
        }

        // Check if the source account has sufficient balance
        if (transferFromCompte.getSolde() < transferAmount) {
            throw new RuntimeException("Insufficient balance in source account");
        }

        // Debit the amount from the source account
        transferFromCompte.setSolde(transferFromCompte.getSolde() - transferAmount);
        compteRepo.save(transferFromCompte);

        // Credit the amount to the destination account
        transferToCompte.setSolde(transferToCompte.getSolde() + transferAmount);
        compteRepo.save(transferToCompte);
    }

}
