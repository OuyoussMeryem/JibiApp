package com.example.jibiapp.models;

import jakarta.persistence.Entity;

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
public class CMIService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nom;
    @OneToMany
    private List<Transaction> transactions= new ArrayList<>();

    public boolean initierTransaction(Paiment paiment){
        return true;
    }
    public boolean validerTransaction(Paiment paiment){
        return true;
    }
    public boolean confirmerTransaction(Paiment paiment){
        return true;
    }
}
