package com.example.jibiapp.models;

import com.example.jibiapp.enums.TypeCompte;
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
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double solde;
    private TypeCompte type_compte;
    @OneToOne(mappedBy = "compte")
    private CarteBancaire carteBancaire;
    @OneToOne(mappedBy = "compte")
    private Client client;
    @OneToOne(mappedBy = "compte")
    private Agence agence;
    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Transaction> transactions=new ArrayList<>();


    public boolean hasSufficientBalance(Double amount) {
        switch (type_compte) {
            case Compte_200:
                return solde >= 200 && solde >= amount;
            case Compte_5000:
                return solde >= 5000 && solde >= amount;
            case Comte_20000:
                return solde >= 20000 && solde >= amount;
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

}
