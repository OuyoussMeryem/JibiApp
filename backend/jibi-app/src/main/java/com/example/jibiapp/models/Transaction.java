package com.example.jibiapp.models;

import com.example.jibiapp.enums.StatusTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Enumerated(EnumType.STRING)
    private StatusTransaction statut;
    private Date date;
    @ManyToOne
    @JsonBackReference
    private CompteApplication compte;
    @OneToOne
    @JsonManagedReference
    private ActionService actionService;
    @OneToOne
    @JsonManagedReference
    private Paiment paiment;


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

}
