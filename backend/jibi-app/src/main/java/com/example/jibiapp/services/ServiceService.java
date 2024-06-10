package com.example.jibiapp.services;

import com.example.jibiapp.repositories.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;

    public com.example.jibiapp.models.Service save(com.example.jibiapp.models.Service service){
        return serviceRepo.save(service);
    }
}
