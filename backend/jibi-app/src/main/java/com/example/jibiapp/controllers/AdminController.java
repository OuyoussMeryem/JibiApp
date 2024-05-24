package com.example.jibiapp.controllers;

import com.example.jibiapp.dto.CreateAgentRequest;
import com.example.jibiapp.models.Agent;
import com.example.jibiapp.models.BackOffice;
import com.example.jibiapp.services.ServiceBackOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/BackOffice")
public class AdminController {

    @Autowired
    private ServiceBackOffice serviceBackOffice;

    @PostMapping("/createAgent")
    public Agent createAgent(
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

        return serviceBackOffice.createAgent(backOffice, request);
    }

    private BackOffice getCurrentBackOffice() {
        return new BackOffice();
    }
}
