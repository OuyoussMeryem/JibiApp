package com.example.jibiapp.models;

import com.example.jibiapp.enums.StatusTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private StatusTransaction statut;
    private Date date;

    @ManyToOne
    private Compte compte; // Compte associé à la transaction

    @OneToOne
    private ActionService actionService; // Facture associée à la transaction

    @OneToOne
    private Paiment paiment;

    @ManyToOne
    private CMIService cmiService; // CMIService associé à la transaction

    public void mettreEnAttente() { this.statut = StatusTransaction.ENATTENTE; }

    public void valider() { this.statut = StatusTransaction.VALIDEE; }

    public void confirmer() {
        this.statut = StatusTransaction.CONFIRMEE;
    }

    public void échouer() {
        this.statut = StatusTransaction.ECHOUEE;
    }

    public void annuler() {
        this.statut = StatusTransaction.ANNULEE;
    }

    // Validation method
    public boolean isValidMontant() {
        return this.montant != null && this.montant >= actionService.getMontant() && this.montant <=compte.getSolde();
    }

    // State check methods
    public boolean isEnAttente() {
        return this.statut == StatusTransaction.ENATTENTE;
    }

    public boolean isValidee() {
        return this.statut == StatusTransaction.VALIDEE;
    }

    public boolean isConfirmee() {
        return this.statut == StatusTransaction.CONFIRMEE;
    }

    public boolean isEchouee() {
        return this.statut == StatusTransaction.ECHOUEE;
    }

    public boolean isAnnulee() {
        return this.statut == StatusTransaction.ANNULEE;
    }

    // Update method
    public void updateMontant(Double newMontant) {
        if (newMontant != null && newMontant > 0) {
            this.montant = newMontant;
        } else {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    // Print transaction details
    public void printDetails() {
        System.out.println("Transaction ID: " + id);
        System.out.println("Montant: " + montant);
        System.out.println("Statut: " + statut);
        System.out.println("Date: " + date);
        if (compte != null) {
            System.out.println("Compte ID: " + compte.getId());
        }
        if (actionService != null) {
            System.out.println("ActionService ID: " + actionService.getId());
        }
        if (paiment != null) {
            System.out.println("Paiment ID: " + paiment.getId());
        }
        if (cmiService != null) {
            System.out.println("CMIService ID: " + cmiService.getId());
        }
    }

    // Set CMIService method
    public void setCmiService(CMIService cmiService) {
        this.cmiService = cmiService;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", montant=" + montant +
                ", statut=" + statut +
                ", date=" + date +
                ", compte=" + compte +
                ", actionService=" + actionService +
                ", paiment=" + paiment +
                ", cmiService=" + cmiService +
                '}';
    }
}
