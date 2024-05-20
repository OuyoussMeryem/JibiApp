package com.example.jibiapp.controllers;

import com.example.jibiapp.models.*;
import com.example.jibiapp.repositories.ActionServiceRepo;
import com.example.jibiapp.repositories.CMIServiceRepo;
import com.example.jibiapp.services.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private ServiceTransaction transactionService; // Assuming you have a TransactionService to handle transactions

    @Autowired
    private CMIServiceRepo cmiServiceRepo; // Autowire CMIServiceRepo

    @Autowired
    private ActionServiceRepo actionServiceRepo; // Autowire ActionServiceRepo

    @PostMapping("/makePayment")
    public ResponseEntity<String> makePayment(@RequestBody Transaction paymentTransaction) {
        try {
            // Fetch the associated CMIService for the ActionService
            ActionService actionService = paymentTransaction.getActionService();
            CMIService cmiService = cmiServiceRepo.findByTransactions_ActionService(actionService);

            // Check if the ActionService is already saved
            if (actionService.getId() == null) {
                // Save the ActionService first
                actionService = actionServiceRepo.save(actionService);
            }

            // Call the makePayment method from TransactionService
            transactionService.makePayment(paymentTransaction.getCompte(), actionService, paymentTransaction.getPaiment(), cmiService);

            // Return success response
            return new ResponseEntity<>("Payment successful", HttpStatus.OK);
        } catch (Exception e) {
            // Return error response in case of any exception
            return new ResponseEntity<>("Payment failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
