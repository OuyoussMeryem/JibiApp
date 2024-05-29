package com.example.jibiapp.controllers;

import com.example.jibiapp.enums.StatusTransaction;
import com.example.jibiapp.models.*;
import com.example.jibiapp.repositories.CompteRepo;
import com.example.jibiapp.request.DepositRequest;
import com.example.jibiapp.request.TransferRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/transact")
public class TransactController {

    @Autowired
    private CompteRepo compteRepo;


    private CMIService cmiService;


    double currentSolde;
    double newSolde;

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
    public ResponseEntity<String> transferAmount(@RequestBody TransferRequest transferRequest) {

        Long transferFromId = transferRequest.getTransferFrom();
        Long transferToId = transferRequest.getTransferTo();
        Double transferAmount = transferRequest.getTransferAmount();


        // Check if transfer amount is positive
        if (transferAmount == null || transferAmount <= 0) {
            return ResponseEntity.badRequest().body("Transfer amount should be greater than zero");
        }

        // Check if transfer is between different accounts
        if (transferFromId.equals(transferToId)) {
            return ResponseEntity.badRequest().body("Cannot transfer to the same account");
        }

        // Retrieve current balances
        double currentBalanceFrom = compteRepo.getCompteSolde(transferFromId);
        double currentBalanceTo = compteRepo.getCompteSolde(transferToId);



        // Check if sufficient balance
        if (currentBalanceFrom < transferAmount ) {
            return ResponseEntity.badRequest().body("Insufficient balance for transfer");
        }

        // Instantiate CMIService
        CMIService cmiService = new CMIService();

        // Initialize transaction
        boolean isTransactionInitialized = cmiService.initierTransaction(new Paiment(transferAmount));

        if (isTransactionInitialized) {
            // Calculate new balances
            double newBalanceFrom = currentBalanceFrom - transferAmount;
            double newBalanceTo = currentBalanceTo + transferAmount;

            // Update balances
            compteRepo.changeSoldeCompte(newBalanceFrom, transferFromId);
            compteRepo.changeSoldeCompte(newBalanceTo, transferToId);

            return ResponseEntity.ok("Amount transferred successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to initialize transaction");
        }
    }

}

