package com.example.jibiapp.services;

import com.example.jibiapp.enums.TypeCompte;
import com.example.jibiapp.models.Client;
import com.example.jibiapp.models.CompteApplication;
import com.example.jibiapp.repositories.CompteApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCompteApplication {

    @Autowired
    private CompteApplicationRepo compteApplicationRepo;
    @Autowired
    private ServiceClient serviceClient;

    public Optional<CompteApplication> findCompteByClientId(Long clientId){
        return compteApplicationRepo.findByClientId(clientId);
    }

    public void save(CompteApplication compteApplication){
        compteApplicationRepo.save(compteApplication);
    }

    public CompteApplication ouvrirCompteApplication(Long clientId, String nomCompte, TypeCompte typeCompte) {
        Client client = serviceClient.findClientById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        CompteApplication compteApplication = new CompteApplication();
        compteApplication.setNom(nomCompte);
        compteApplication.setSolde(0.0);
        compteApplication.setType_compte(typeCompte);
        compteApplication.setClient(client);

        save(compteApplication);

        client.setCompteApplication(compteApplication);
        serviceClient.save(client);

        return compteApplication;
    }
}
