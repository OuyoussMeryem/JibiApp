package com.example.jibiapp.controllers;


import com.example.jibiapp.models.ActionService;
import com.example.jibiapp.services.ServiceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid ;
import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/action-services")
public class ActionServiceController {

    @Autowired
    private ServiceActionService actionServiceService;

    @PostMapping
    public ResponseEntity<ActionService> createActionService(@Valid @RequestBody ActionService actionService) {
        // Create the ActionService entity
        ActionService createdActionService = actionServiceService.createActionService(actionService);
        // Return the created ActionService with HTTP status code 201 (Created)
        return new ResponseEntity<>(createdActionService, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActionService>> getAllActionServices() {
        // Get all ActionService entities
        List<ActionService> actionServices = actionServiceService.getAllActionServices();
        // Return the list of ActionService entities with HTTP status code 200 (OK)
        return new ResponseEntity<>(actionServices, HttpStatus.OK);
    }
}
