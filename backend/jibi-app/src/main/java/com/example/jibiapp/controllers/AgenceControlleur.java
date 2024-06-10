package com.example.jibiapp.controllers;

import com.example.jibiapp.models.ActionService;
import com.example.jibiapp.services.ServiceAgence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/agence")
public class AgenceControlleur {

   @Autowired
   private ServiceAgence serviceAgence;


    @PostMapping("/createActionService")
    public ResponseEntity<ActionService> createActionService(
            @RequestParam Long clientId,
            @RequestParam Long serviceId,
            @RequestParam Double montant) {
        ActionService actionService = serviceAgence.createActionService(clientId, serviceId, montant);
        return new ResponseEntity<>(actionService, HttpStatus.CREATED);
    }

}
