package com.example.jibiapp.models;

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
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "agence" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Agent> agents=new ArrayList<>();
    @OneToMany(mappedBy = "agence" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Service> services=new ArrayList<>();
    @OneToOne
    private Compte compte;
    @OneToOne
    private Image image;
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private List<ClientAgence> clientAgences = new ArrayList<>();
    
}
