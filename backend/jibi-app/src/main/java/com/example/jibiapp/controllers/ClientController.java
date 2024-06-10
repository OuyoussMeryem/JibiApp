package com.example.jibiapp.controllers;

import com.example.jibiapp.dto.DemandeIscriptionRequest;
import com.example.jibiapp.enums.TypeCompte;
import com.example.jibiapp.models.ActionService;
import com.example.jibiapp.models.CompteApplication;
import com.example.jibiapp.models.Service;
import com.example.jibiapp.services.ServiceAgence;
import com.example.jibiapp.services.ServiceClient;
import com.example.jibiapp.services.ServiceCompteApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ServiceClient serviceClient;
    @Autowired
    private ServiceAgence serviceAgence;
    @Autowired
    private ServiceCompteApplication serviceCompteApplication;

    @GetMapping("/test")
    public String test() {
        return "hi from client";
    }

    @PostMapping("/demandeInscription/{email}")
    public String demandeInscription(
            @PathVariable String email,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String emailClient,
            @RequestParam String telephone,
            @RequestParam MultipartFile pieceIdentiteFaceOne,
            @RequestParam MultipartFile pieceIdentiteFaceTwo,
            @RequestParam String numPieceIdentite,
            @RequestParam String dateNaissance,
            @RequestParam String adresse) {

        DemandeIscriptionRequest client = new DemandeIscriptionRequest();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(emailClient);
        client.setTelephone(telephone);
        client.setPieceIdentiteFaceOne(pieceIdentiteFaceOne);
        client.setPieceIdentiteFaceTwo(pieceIdentiteFaceTwo);
        client.setNumPieceIdentite(numPieceIdentite);
        client.setDateNaissance(dateNaissance);
        client.setAdresse(adresse);
        serviceClient.demandeInscription(client, email);
        return "La demande a été envoyée avec succès";
    }

    @PutMapping("/{clientId}/modifierUsernamePassword")
    public String  modifierUsernamePassword(@PathVariable Long clientId,
                                            @RequestParam String nouveauUsername,
                                            @RequestParam String nouveauPassword){
        try {
            serviceClient.modifierUsernameAndPasswordForAgence(clientId,nouveauUsername,nouveauPassword);
            return "Identifiants mis à jour avec succès.";

        }catch (Exception e){
            return "Erreur lors de la mise à jour des identifiants : " + e.getMessage();
        }
    }
    @PutMapping("/{clientId}/modifierPassword")
    public String  modifierPassword(@PathVariable Long clientId,
                                            @RequestParam String nouveauPassword){
        try {
            serviceClient.modifierPassword(clientId,nouveauPassword);
            return "Identifiants mis à jour avec succès.";

        }catch (Exception e){
            return "Erreur lors de la mise à jour des identifiants : " + e.getMessage();
        }
    }

    @PostMapping("/{clientId}/ouvrir-compte-application")
    public ResponseEntity<CompteApplication> ouvrirCompteApplication(
            @PathVariable Long clientId,
            @RequestParam TypeCompte typeCompte,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String telephone,
            @RequestParam String email) {

        CompteApplication compteApplication = serviceCompteApplication.ouvrirCompteApplication(clientId, typeCompte,nom,prenom,telephone,email);
        return ResponseEntity.ok(compteApplication);
    }

    @GetMapping("/valableServicesByAgence/{agenceId}")
    public ResponseEntity<List<Service>> getValableServicesByAgence(@PathVariable Long agenceId) {
        List<Service> services = serviceAgence.getValableServicesByAgence(agenceId);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/{clientId}/{agenceId}/nonPayeeActionServicesByClientAndAgence")
    public ResponseEntity<List<ActionService>> getNonPayeeActionServicesByClientAndAgence(
            @PathVariable Long clientId,
            @PathVariable Long agenceId) {
        List<ActionService> actionServices = serviceClient.getNonPayeeActionServicesByClientAndAgence(clientId, agenceId);
        return new ResponseEntity<>(actionServices, HttpStatus.OK);
    }

    @PostMapping("/{clientId}/payActionService/{actionServiceId}")
    public ResponseEntity<String> payActionService(
            @PathVariable Long clientId,
            @PathVariable Long actionServiceId) {
        boolean success = serviceClient.payActionService(clientId, actionServiceId);
        if (success) {
            return new ResponseEntity<>("Payment successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{clientId}/payeeActionServices")
    public ResponseEntity<List<ActionService>> getPayeeActionServicesByClient(@PathVariable Long clientId) {
        List<ActionService> actionServices = serviceClient.getPayeeActionServicesByClient(clientId);
        return new ResponseEntity<>(actionServices, HttpStatus.OK);
    }

    @GetMapping("/{actionServiceId}/compareAmount")
    public ResponseEntity<Boolean> compareAmountWithActionService(
            @PathVariable Long actionServiceId,
            @RequestParam Double amount) {
        boolean isAmountEqual = serviceClient.VerifierAmountWithActionService(actionServiceId, amount);
        return new ResponseEntity<>(isAmountEqual, HttpStatus.OK);
    }

}
