package com.example.jibiapp.models;

import com.example.jibiapp.enums.StatusTransaction;
import com.example.jibiapp.enums.serviceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private serviceType type;
    private boolean valable;
    @ManyToOne
    @JsonBackReference
    private Agence agence;
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ActionService> actionService=new ArrayList<>();
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Paiment> paiment=new ArrayList<>();

    public void facture() { this.type = serviceType.facture; }

    public void donation() { this.type = serviceType.donation; }

    public void recharge() {this.type = serviceType.recharge; }
}
