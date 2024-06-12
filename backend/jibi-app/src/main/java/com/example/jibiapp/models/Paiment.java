package com.example.jibiapp.models;

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
@NoArgsConstructor
@AllArgsConstructor
public class Paiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private LocalDateTime datePaiment;
    @OneToOne(mappedBy = "paiment")
    @JsonBackReference
    private Transaction transaction;
    @ManyToOne
    @JsonBackReference
    private Service service;
    @ManyToOne
    @JsonBackReference
    private Client client;

}
