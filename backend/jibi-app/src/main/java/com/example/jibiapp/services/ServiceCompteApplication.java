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
    @Autowired
    private ServiceCMIService serviceCMIService;

    public Optional<CompteApplication> findCompteByClientId(Long clientId){
        return compteApplicationRepo.findByClientId(clientId);
    }

    public void save(CompteApplication compteApplication){
        compteApplicationRepo.save(compteApplication);
    }

    public CompteApplication ouvrirCompteApplication(Long clientId, TypeCompte typeCompte, String nom, String prenom, String telephone, String email) {
        Client client = serviceClient.findClientById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        boolean infoValide = serviceCMIService.verifierInformationsClient(client, nom, prenom, telephone, email);
        if (!infoValide) {
            throw new RuntimeException("Les informations du client ne correspondent pas");
        }
        CompteApplication compteApplication = new CompteApplication();
        compteApplication.setSolde(0.0);
        compteApplication.setType_compte(typeCompte);
        compteApplication.setClient(client);

        save(compteApplication);

        client.setCompteApplication(compteApplication);
        serviceClient.save(client);

        return compteApplication;
    }
}
