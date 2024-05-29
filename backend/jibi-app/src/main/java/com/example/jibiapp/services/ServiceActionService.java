package com.example.jibiapp.services;

import com.example.jibiapp.models.ActionService;
import com.example.jibiapp.models.Transaction;
import com.example.jibiapp.repositories.ActionServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceActionService {

    @Autowired
    private ActionServiceRepo actionServiceRepo;
    public List<ActionService> getAllActionServices() {
        return actionServiceRepo.findAll();
    }

    public ActionService createActionService(ActionService actionService) {
        // Save the ActionService entity
        return actionServiceRepo.save(actionService);
    }

}
