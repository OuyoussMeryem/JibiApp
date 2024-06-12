package com.example.jibiapp.models;

import com.example.jibiapp.enums.EtatActionService;
import com.example.jibiapp.enums.serviceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Enumerated(EnumType.STRING)
    private EtatActionService etat;
    private LocalDateTime date;
    @ManyToOne
    @JsonBackReference
    private Client client;
    @ManyToOne
    @JsonBackReference
    private Service service;
    @OneToOne(mappedBy = "actionService")
    @JsonBackReference
    private Transaction transaction;


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
