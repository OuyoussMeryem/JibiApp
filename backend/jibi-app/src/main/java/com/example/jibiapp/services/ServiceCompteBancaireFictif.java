package com.example.jibiapp.services;

import com.example.jibiapp.models.CompteApplication;
import com.example.jibiapp.models.CompteBancaireFictif;
import com.example.jibiapp.repositories.CompteBancaireFictifRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCompteBancaireFictif {

    @Autowired
    private CompteBancaireFictifRepo compteBancaireFictifRepo;

    public Optional<CompteBancaireFictif> findCompteBancaireByClientId(Long clientId){
        return compteBancaireFictifRepo.findByClientId(clientId);
    }

    public void save(CompteBancaireFictif compteBancaireFictif){
        compteBancaireFictifRepo.save(compteBancaireFictif);
    }
}
