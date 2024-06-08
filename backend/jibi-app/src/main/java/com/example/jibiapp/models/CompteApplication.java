package com.example.jibiapp.models;

import com.example.jibiapp.enums.TypeCompte;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class CompteApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double solde;
    @Enumerated(EnumType.STRING)
    private TypeCompte type_compte;
    @OneToOne
    private Client client;
    @OneToOne
    private Agence agence;
    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> transactions=new ArrayList<>();

    private static final double MIN_SOLDE_COMPTE_200 = 200.0;
    private static final double MIN_SOLDE_COMPTE_5000 = 5000.0;
    private static final double MIN_SOLDE_COMPTE_20000 = 20000.0;

    public boolean hasSufficientBalance(Double amount) {
        switch (type_compte) {
            case Compte_200:
                return solde >= MIN_SOLDE_COMPTE_200 + amount;
            case Compte_5000:
                return solde >= MIN_SOLDE_COMPTE_5000 + amount;
            case Comte_20000:
                return solde >= MIN_SOLDE_COMPTE_20000 + amount;
            default:
                return false;
        }
    }


    public boolean debitAccount(Double amount) {
        if (hasSufficientBalance(amount)) {
            solde -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void creditAccount(Double amount) {
        solde += amount;
    }

}
