package com.example.jibiapp.controllers;

import com.example.jibiapp.exception.ExceptionMetier;
import com.example.jibiapp.models.Transaction;
import com.example.jibiapp.repositories.CMIServiceRepo;
import com.example.jibiapp.repositories.CompteRepo;
import com.example.jibiapp.repositories.PaimentRepo;
import com.example.jibiapp.repositories.TransactionRepo;
import com.example.jibiapp.request.DepositRequest;
import com.example.jibiapp.request.TransferRequest;
import com.example.jibiapp.services.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transact")
public class TransactController {

    @Autowired
    private CompteRepo compteRepo;

    double currentSolde;
    double newSolde;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private CMIServiceRepo cmiServiceRepo;

    @Autowired
    private PaimentRepo paimentRepo;

    @Autowired
    private ServiceTransaction transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest depositRequest){
        if (depositRequest.getDepositAmount() == 0 || depositRequest.getIdCompte() == 0){
            return new ResponseEntity<>("Deposit or Account id can't be empty", HttpStatus.BAD_REQUEST);
        }

        long compte = depositRequest.getIdCompte();
        double deposit = depositRequest.getDepositAmount();

        if (deposit == 0){
            return new ResponseEntity<>("Deposit can't be 0 (zero)", HttpStatus.BAD_REQUEST);
        }

        currentSolde = compteRepo.getCompteSolde(compte);
        newSolde = currentSolde + deposit ;

        compteRepo.changeSoldeCompte(newSolde,compte);

        return new ResponseEntity<>("Deposit added", HttpStatus.OK);
    }



    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(@RequestBody TransferRequest transferRequest) {
        try {
            Transaction transaction = transactionService.createAndSaveTransaction(
                    transferRequest.getTransferFrom(),
                    transferRequest.getTransferTo(),
                    transferRequest.getTransferAmount(),
                    transferRequest.getCmiServiceId(),
                    transferRequest.getServiceId(),
                    transferRequest.getActionServiceId()
            );
            return ResponseEntity.ok().body("Transaction completed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


 /*
    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity<String> transferAmount(@RequestBody TransferRequest transferRequest) {
        Long transferFromId = transferRequest.getTransferFrom();
        Long transferToId = transferRequest.getTransferTo();
        Double transferAmount = transferRequest.getTransferAmount();
        Long cmiServiceId = transferRequest.getCmiServiceId();

        // Check if transfer amount is positive
        if (transferAmount == null || transferAmount <= 0) {
            return ResponseEntity.badRequest().body("Transfer amount should be greater than zero");
        }

        // Check if transfer is between different accounts
        if (transferFromId.equals(transferToId)) {
            return ResponseEntity.badRequest().body("Cannot transfer to the same account");
        }

        // Retrieve source and destination accounts
        Optional<Compte> optionalTransferFromCompte = compteRepo.findById(transferFromId);
        Optional<Compte> optionalTransferToCompte = compteRepo.findById(transferToId);

        if (!optionalTransferFromCompte.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid transfer source compte ID");
        }

        if (!optionalTransferToCompte.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid transfer destination compte ID");
        }

        Compte transferFromCompte = optionalTransferFromCompte.get();
        Compte transferToCompte = optionalTransferToCompte.get();

        // Check if sufficient balance
        if (transferFromCompte.getSolde() < transferAmount) {
            return ResponseEntity.badRequest().body("Insufficient balance for transfer");
        }

        // Retrieve CMIService from database
        Optional<CMIService> optionalCMIService = cmiServiceRepo.findById(cmiServiceId);

        if (!optionalCMIService.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid CMIService ID");
        }

        CMIService cmiService = optionalCMIService.get();

       // Create and save Paiment entity
        Paiment paiment = new Paiment();
        paiment.setMontant(transferRequest.getTransferAmount());
        paimentRepo.save(paiment);

        // Initialize transaction
        boolean isTransactionInitialized = cmiService.initierTransaction(paiment);

        Transaction transaction = new Transaction();

        if (isTransactionInitialized) {
            try {
                // Create a single transaction representing both credit and debit operations
                transaction.setDate(new Date());
                transaction.setCmiService(cmiService);

                // Set the saved Paiment entity
                transaction.setPaiment(paiment);

                // Debit the amount from the source account
                transferFromCompte.debitAccount(transferAmount);

                // Credit the amount to the destination account
                transferToCompte.creditAccount(transferAmount);

                // Set attributes for the transaction
                transaction.setMontant(transferAmount);
                transaction.mettreEnAttente();
                transaction.setCompte(transferFromCompte); // or transferToCompte, depending on your logic

                // Save the single transaction representing both operations
                transactionRepo.save(transaction);

                // Confirm and save the transaction
                transaction.valider();
                transactionRepo.save(transaction);

                return ResponseEntity.ok().body("Transaction completed successfully");
            } catch (Exception e) {
                // Rollback transactions and update the transaction state accordingly
                transaction.échouer();
                transactionRepo.save(transaction);

                // Log error and send appropriate response
                //logger.error("Transaction failed: ", e);
                return ResponseEntity.badRequest().body("Transaction failed. Please try again.");
            }
        } else {
            return ResponseEntity.badRequest().body("Failed to initialize transaction. Please try again.");
        }
    }*/

}

