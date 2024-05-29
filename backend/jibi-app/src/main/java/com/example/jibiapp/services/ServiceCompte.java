package com.example.jibiapp.services;

import com.example.jibiapp.models.Compte;
import com.example.jibiapp.repositories.CompteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCompte {

    private final CompteRepo compteRepo;

    @Autowired
    public ServiceCompte(CompteRepo compteRepo) {
        this.compteRepo = compteRepo;
    }

    public List<Compte> getAllComptes() {
        return compteRepo.findAll();
    }

    public Compte createCompte(Compte compte) {
        return compteRepo.save(compte);
    }
}
