package com.example.jibiapp.models;

import com.example.jibiapp.enums.EtatActionService;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CMIService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nom;
    @OneToMany
    private List<Transaction> transactions= new ArrayList<>();

    public boolean initierTransaction(Paiment paiment){
        return true;
    }
    public boolean validerTransaction(Compte compte, ActionService actionService, Paiment paiment){
        // Check if the account has sufficient balance for the payment
        if (!compte.hasSufficientBalance(paiment.getMontant())) {
            return false; // Insufficient balance
        }
        // Check if the action service is in a valid state (e.g., "PAYEE")
        if (!actionService.getEtat().equals(EtatActionService.PAYEE)) {
            return false; // Action service not in a valid state
        }

        // Additional validation logic can be added here if needed

        // If all conditions pass, return true
        return true;
    }
    public boolean confirmerTransaction(Paiment paiment){
        return true;
    }
}
