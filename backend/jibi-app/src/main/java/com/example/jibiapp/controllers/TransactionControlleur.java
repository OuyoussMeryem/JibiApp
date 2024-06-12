package com.example.jibiapp.controllers;

import com.example.jibiapp.models.Transaction;
import com.example.jibiapp.services.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControlleur {

    @Autowired
    private ServiceTransaction transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Transaction>> getTransactionsByClient(@PathVariable Long clientId) {
        List<Transaction> transactions = transactionService.getTransactionsByClient(clientId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
