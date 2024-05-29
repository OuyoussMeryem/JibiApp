package com.example.jibiapp.services;

import com.example.jibiapp.models.Services;
import com.example.jibiapp.repositories.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServices {

    @Autowired
    private ServiceRepo serviceRepo;

    public List<Services> getAllServices() {
        return serviceRepo.findAll();
    }

    public Services createService(Services service) {
        // Save the Service entity
        return serviceRepo.save(service);
    }
}
