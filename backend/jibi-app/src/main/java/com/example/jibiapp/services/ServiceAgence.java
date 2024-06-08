package com.example.jibiapp.services;

import com.example.jibiapp.models.Agence;
import com.example.jibiapp.repositories.AgenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAgence {
    @Autowired
    private AgenceRepo agenceRepo;

    public Optional<Agence> findAgenceByid(Long id){
        return agenceRepo.findById(id);
    }

    public Agence save(Agence agence){return agenceRepo.save(agence); }

    public List<Agence> getAllAgence(){
        return agenceRepo.findAll();
    }


}
