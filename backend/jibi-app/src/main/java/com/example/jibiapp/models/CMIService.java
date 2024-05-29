package com.example.jibiapp.models;

import com.example.jibiapp.enums.StatusTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CMIService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "cmiService", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public boolean initierTransaction(Paiment paiment) {
        // Validate the payment
        if (paiment == null || paiment.getMontant() == null || paiment.getMontant() <= 0) {
            throw new IllegalArgumentException("Invalid payment information");
        }

        // Create a new transaction and set its initial status
        Transaction transaction = new Transaction();
        transaction.setPaiment(paiment);
        transaction.setStatut(StatusTransaction.ENATTENTE);
        transaction.setDate(new Date());
        transaction.setMontant(paiment.getMontant());

        // Link transaction with CMIService and add to list
        transaction.setCmiService(this);
        transactions.add(transaction);

        // Link the transaction to the payment
        paiment.setTransaction(transaction);

        // Assuming other logic to actually initiate the transaction, like communicating with a payment gateway
        return true;
    }

    public boolean validerTransaction(Paiment paiment) {
        // Validate the payment
        if (paiment == null) {
            System.out.println("Validation failed: Payment is null");
            throw new IllegalArgumentException("Invalid payment or transaction information");
        }
        if (paiment.getMontant() <= 0) {
            System.out.println("Validation failed: Payment amount is less than or equal to zero");
            throw new IllegalArgumentException("Invalid payment or transaction information");
        }

        // Retrieve the transaction
        Transaction transaction = paiment.getTransaction();
        if (transactions.contains(transaction) && transaction.isEnAttente()) {
            // Update transaction status to 'VALIDEE'
            transaction.valider();

            // Assuming other logic to validate the transaction with a payment gateway or other systems
            return true;
        }
        return false;
    }

    public boolean confirmerTransaction(Paiment paiment) {
        // Validate the payment
        if (paiment == null || paiment.getTransaction() == null) {
            throw new IllegalArgumentException("Invalid payment or transaction information");
        }

        // Retrieve the transaction
        Transaction transaction = paiment.getTransaction();
        if (transactions.contains(transaction) && transaction.isValidee()) {
            // Update transaction status to 'CONFIRMEE'
            transaction.confirmer();

            // Assuming other logic to confirm the transaction with a payment gateway or other systems
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CMIService{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", transactions=" + transactions +
                '}';
    }


}
