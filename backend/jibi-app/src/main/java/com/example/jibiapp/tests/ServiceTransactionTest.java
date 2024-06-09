package com.example.jibiapp.tests;

import com.example.jibiapp.models.Transaction;
import com.example.jibiapp.services.ServiceTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)  // For JUnit 4

@SpringBootTest
public class ServiceTransactionTest {
    @Autowired
    private ServiceTransaction transactionService;

    @Test
    public void testSaveTransaction() {
        Transaction transaction = new Transaction();
        // Set transaction fields
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        assertNotNull(savedTransaction.getId());
    }
}
