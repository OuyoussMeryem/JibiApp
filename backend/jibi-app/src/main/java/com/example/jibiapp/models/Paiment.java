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
    private Service service;
    @ManyToOne
    private Client client;

}
