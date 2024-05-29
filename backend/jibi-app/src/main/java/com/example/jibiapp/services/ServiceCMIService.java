package com.example.jibiapp.services;

import com.example.jibiapp.models.CMIService;
import com.example.jibiapp.repositories.CMIServiceRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceCMIService {

    private final CMIServiceRepo cmiServiceRepo;

    @Autowired
    public ServiceCMIService(CMIServiceRepo cmiServiceRepo) {
        this.cmiServiceRepo = cmiServiceRepo;
    }

    @Transactional
    public List<CMIService> getAllCMIServices() {
        List<CMIService> cmiServices = cmiServiceRepo.findAll();
        // Initialize the transactions collection for each CMIService entity
        cmiServices.forEach(cmiService -> Hibernate.initialize(cmiService.getTransactions()));
        return cmiServices;
    }
    @Transactional
    public CMIService createCMIService(CMIService cmiService) {
        return cmiServiceRepo.save(cmiService);
    }


}
