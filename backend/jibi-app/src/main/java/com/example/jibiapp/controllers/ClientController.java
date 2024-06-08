package com.example.jibiapp.controllers;

import com.example.jibiapp.dto.DemandeIscriptionRequest;
import com.example.jibiapp.enums.TypeCompte;
import com.example.jibiapp.models.CompteApplication;
import com.example.jibiapp.services.ServiceClient;
import com.example.jibiapp.services.ServiceCompteApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ServiceClient serviceClient;
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

}
