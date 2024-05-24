package com.example.jibiapp.services;

import com.example.jibiapp.models.Agence;
import com.example.jibiapp.models.Client;
import com.example.jibiapp.models.ClientAgence;
import com.example.jibiapp.repositories.ClientAgenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClientAgence {

    @Autowired
    private ServiceClient serviceClient;
    @Autowired
    private ServiceAgence serviceAgence;
    @Autowired
    private ClientAgenceRepo clientAgenceRepo;


    public void associateClientWithAgence(Long clientId, Long agenceId) {
        Client client = serviceClient.findClientById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Agence agence = serviceAgence.findAgenceByid(agenceId).orElseThrow(() -> new RuntimeException("Agence not found"));

        ClientAgence clientAgence = new ClientAgence(client, agence);
        clientAgenceRepo.save(clientAgence);
    }


}
