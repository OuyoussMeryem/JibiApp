package com.example.jibiapp.controllers;

import com.example.jibiapp.dto.CreateAgenceRequest;
import com.example.jibiapp.dto.CreateAgentRequest;
import com.example.jibiapp.models.Agence;
import com.example.jibiapp.models.Agent;
import com.example.jibiapp.models.BackOffice;
import com.example.jibiapp.responses.AuthenticationResponse;
import com.example.jibiapp.services.ServiceAgent;
import com.example.jibiapp.services.ServiceBackOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/BackOffice")
public class AdminController {

    @Autowired
    private ServiceBackOffice serviceBackOffice;
    @Autowired
    private ServiceAgent serviceAgent;


    @GetMapping("/listAgents")
    public ResponseEntity<List<Agent>> listAllAgents() {
        List<Agent> agents = serviceAgent.findAllAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    private BackOffice getCurrentBackOffice() {
        return new BackOffice();
    }
    @PostMapping("/createAgent")
    public  ResponseEntity<AuthenticationResponse> createAgent(
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String telephone,
            @RequestParam MultipartFile pieceIdentiteFaceOne,
            @RequestParam MultipartFile pieceIdentiteFaceTwo,
            @RequestParam String numPieceIdentite,
            @RequestParam String dateNaissance,
            @RequestParam String adresse,
            @RequestParam String numPattente) {


        BackOffice backOffice = getCurrentBackOffice();

        CreateAgentRequest request = new CreateAgentRequest();
        request.setNom(nom);
        request.setPrenom(prenom);
        request.setEmail(email);
        request.setTelephone(telephone);
        request.setPieceIdentiteFaceOne(pieceIdentiteFaceOne);
        request.setPieceIdentiteFaceTwo(pieceIdentiteFaceTwo);
        request.setNumPieceIdentite(numPieceIdentite);
        request.setDateNaissance(dateNaissance);
        request.setAdresse(adresse);
        request.setNumPattente(numPattente);

        return ResponseEntity.ok(serviceBackOffice.createAgent(backOffice,request));
    }

    @PostMapping("/createAgence")
    public ResponseEntity<Agence> createAgence(
            @RequestParam String nom,
            @RequestParam MultipartFile image) {

        CreateAgenceRequest request =new CreateAgenceRequest();
        request.setNom(nom);
        request.setPieceIdentiteFaceOne(image);
        Agence agence=serviceBackOffice.createAgence(request);
        return new ResponseEntity<>(agence, HttpStatus.CREATED);
    }
    @GetMapping("/agences")
    public ResponseEntity<List<Agence>> getAllAgences() {
        List<Agence> agences = serviceBackOffice.getAllAgences();
        return new ResponseEntity<>(agences, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAgent/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id) {
        try {
            serviceBackOffice.deleteAgent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateAgent/{id}")
    public ResponseEntity<?> updateAgent(
            @PathVariable Long id,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String telephone,
            @RequestParam MultipartFile pieceIdentiteFaceOne,
            @RequestParam MultipartFile pieceIdentiteFaceTwo,
            @RequestParam String numPieceIdentite,
            @RequestParam String dateNaissance,
            @RequestParam String adresse,
            @RequestParam String numPattente) {

        CreateAgentRequest request = new CreateAgentRequest();
        request.setNom(nom);
        request.setPrenom(prenom);
        request.setEmail(email);
        request.setTelephone(telephone);
        request.setPieceIdentiteFaceOne(pieceIdentiteFaceOne);
        request.setPieceIdentiteFaceTwo(pieceIdentiteFaceTwo);
        request.setNumPieceIdentite(numPieceIdentite);
        request.setDateNaissance(dateNaissance);
        request.setAdresse(adresse);
        request.setNumPattente(numPattente);

        try {
            Agent agent = serviceBackOffice.updateAgent(id, request);
            return new ResponseEntity<>(agent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
