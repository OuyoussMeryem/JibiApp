package com.example.jibiapp.services;

import com.example.jibiapp.enums.EtatActionService;
import com.example.jibiapp.models.ActionService;
import com.example.jibiapp.models.Agence;
import com.example.jibiapp.models.Client;
import com.example.jibiapp.repositories.ActionServiceRepo;
import com.example.jibiapp.repositories.AgenceRepo;
import com.example.jibiapp.repositories.ClientRepo;
import com.example.jibiapp.repositories.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceAgence {
    @Autowired
    private AgenceRepo agenceRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ServiceRepo serviceRepo;
    @Autowired
    private ActionServiceRepo actionServiceRepo;

    public Optional<Agence> findAgenceByid(Long id){
        return agenceRepo.findById(id);
    }

    public Agence save(Agence agence){return agenceRepo.save(agence); }

    public List<Agence> getAllAgence(){
        return agenceRepo.findAll();
    }



    public List<com.example.jibiapp.models.Service> getValableServicesByAgence(Long agenceId) {
        Agence agence = findAgenceByid(agenceId)
                .orElseThrow(() -> new RuntimeException("Agence not found"));

        return agence.getServices().stream()
                .filter(com.example.jibiapp.models.Service::isValable)
                .collect(Collectors.toList());
    }

    public ActionService createActionService(Long clientId, Long serviceId, Double montant) {
        Optional<Client> clientOpt = clientRepo.findById(clientId);
        Optional<com.example.jibiapp.models.Service> serviceOpt = serviceRepo.findById(serviceId);

        if (clientOpt.isPresent() && serviceOpt.isPresent()) {
            ActionService actionService = new ActionService();
            actionService.setClient(clientOpt.get());
            actionService.setService(serviceOpt.get());
            actionService.setMontant(montant);
            actionService.setEtat(EtatActionService.NONPAYEE);
            actionService.setDate(LocalDateTime.now());
            return actionServiceRepo.save(actionService);
        } else {
            throw new RuntimeException("Client or Service not found");
        }
    }
}
