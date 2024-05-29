package com.example.jibiapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private LocalDateTime datePaiment;
    @OneToOne(mappedBy = "paiment")
    private Transaction transaction;
    @ManyToOne
    private Services service;
    @ManyToOne
    private Client client;

    public Paiment(Double montant) {
        this.montant = montant;
    }
    @Override
    public String toString() {
        return "Paiment{" +
                "id=" + id +
                ", montant=" + montant +
                ", datePaiment=" + datePaiment +
                ", transaction=" + transaction +
                ", service=" + service +
                ", client=" + client +
                '}';
    }
}
