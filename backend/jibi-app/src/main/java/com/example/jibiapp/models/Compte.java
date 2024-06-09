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
    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions=new ArrayList<>();


  /*  public boolean hasSufficientBalance(Double amount) {

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

*/

    private static final double MIN_SOLDE_COMPTE_200 = 200.0;
    private static final double MIN_SOLDE_COMPTE_5000 = 5000.0;
    private static final double MIN_SOLDE_COMPTE_20000 = 20000.0;

    public boolean hasSufficientBalance(Double amount) {
        double minBalanceRequired = 0.0;

        switch (type_compte) {
            case Compte_200:
                minBalanceRequired = MIN_SOLDE_COMPTE_200;
                break;
            case Compte_5000:
                minBalanceRequired = MIN_SOLDE_COMPTE_5000;
                break;
            case Compte_20000:
                minBalanceRequired = MIN_SOLDE_COMPTE_20000;
                break;
            default:
                return false; // Unknown account type, insufficient balance
        }

        return solde >= minBalanceRequired + amount;
    }

    public void debitAccount(Double amount) {
            solde -= amount;
    }

    public void creditAccount(Double amount) {
        solde += amount;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", solde=" + solde +
                ", type_compte=" + type_compte +
                ", carteBancaire=" + carteBancaire +
                ", client=" + client +
                ", agence=" + agence +
                ", transactions=" + transactions +
                '}';
    }
}
