package com.example.jibiapp.models;

import com.example.jibiapp.enums.EtatActionService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActionService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private EtatActionService etat ;
    private LocalDateTime date;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Services service;
    @OneToOne(mappedBy = "actionService")
    private Transaction transaction;

    @Override
    public String toString() {
        return "ActionService{" +
                "id=" + id +
                ", montant=" + montant +
                ", etat=" + etat +
                ", date=" + date +
                ", client=" + client +
                ", service=" + service +
                ", transaction=" + transaction +
                '}';
    }
    public void mettreEnAttente() {
        this.etat = EtatActionService.ENATTEND;
    }


    public void payer() {
        this.etat = EtatActionService.PAYEE;
    }


    public void marquerNonPayee() {
        this.etat = EtatActionService.NONPAYEE;
    }


    public void annuler() {this.etat = EtatActionService.ANNULER; }


}
