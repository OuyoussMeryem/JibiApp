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



}

