package com.example.jibiapp.repositories;

import com.example.jibiapp.models.Client;
import com.example.jibiapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
    List<Transaction> findByCompteClient(Client client);
}
