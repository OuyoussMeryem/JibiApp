package com.example.jibiapp.services;

import com.example.jibiapp.models.Client;
import com.example.jibiapp.models.Transaction;
import com.example.jibiapp.repositories.ClientRepo;
import com.example.jibiapp.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTransaction {
    @Autowired
    private TransactionRepo transactionRepository;

    @Autowired
    private ClientRepo clientRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + clientId));
        return transactionRepository.findByCompteClient(client);
    }
}
