package com.example.jibiapp.services;

import com.example.jibiapp.models.Paiment;
import com.example.jibiapp.repositories.PaimentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePaiment {

    private final PaimentRepo paimentRepo;

    @Autowired
    public ServicePaiment(PaimentRepo paimentRepo) {
        this.paimentRepo = paimentRepo;
    }

    public List<Paiment> getAllPaiments() {
        return paimentRepo.findAll();
    }

    public Paiment createPaiment(Paiment paiment) {
        return paimentRepo.save(paiment);
    }
}
