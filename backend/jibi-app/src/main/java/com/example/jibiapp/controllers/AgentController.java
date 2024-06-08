package com.example.jibiapp.controllers;

import com.example.jibiapp.dto.CreateClientRequest;
import com.example.jibiapp.models.Client;
import com.example.jibiapp.responses.AuthenticationResponse;
import com.example.jibiapp.services.ServiceAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private ServiceAgent serviceAgent;



    @GetMapping("/test")
    public String test() {
        return "hi from Agent";
    }

    @PostMapping("/{agentId}/createClient")
    public ResponseEntity<AuthenticationResponse> createClient(
            @PathVariable Long agentId,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String telephone,
            @RequestParam MultipartFile pieceIdentiteFaceOne,
            @RequestParam MultipartFile pieceIdentiteFaceTwo,
            @RequestParam String numPieceIdentite,
            @RequestParam String dateNaissance,
            @RequestParam String adresse){


        CreateClientRequest request=new CreateClientRequest();
        request.setNom(nom);
        request.setPrenom(prenom);
        request.setEmail(email);
        request.setTelephone(telephone);
        request.setPieceIdentiteFaceOne(pieceIdentiteFaceOne);
        request.setPieceIdentiteFaceTwo(pieceIdentiteFaceTwo);
        request.setNumPieceIdentite(numPieceIdentite);
        request.setDateNaissance(dateNaissance);
        request.setAdresse(adresse);


        return ResponseEntity.ok(serviceAgent.createClient(agentId,request));
    }

    @GetMapping("/listClients")
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = serviceAgent.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

  @PutMapping("/{agentId}/modifierUsernamePassword")
  public String  modifierUsernamePassword(@PathVariable Long agentId,
                                          @RequestParam String nouveauUsername,
                                          @RequestParam String nouveauPassword){
        try {
            serviceAgent.modifierUsernameAndPassword(agentId,nouveauUsername,nouveauPassword);
            return "Identifiants mis à jour avec succès.";

        }catch (Exception e){
            return "Erreur lors de la mise à jour des identifiants : " + e.getMessage();
        }
  }

    @PutMapping("/updateClient/{clientId}")
    public ResponseEntity<?> updateClient(
            @PathVariable Long clientId,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String telephone,
            @RequestParam MultipartFile pieceIdentiteFaceOne,
            @RequestParam MultipartFile pieceIdentiteFaceTwo,
            @RequestParam String numPieceIdentite,
            @RequestParam String dateNaissance,
            @RequestParam String adresse) {

        CreateClientRequest request = new CreateClientRequest();
        request.setNom(nom);
        request.setPrenom(prenom);
        request.setEmail(email);
        request.setTelephone(telephone);
        request.setPieceIdentiteFaceOne(pieceIdentiteFaceOne);
        request.setPieceIdentiteFaceTwo(pieceIdentiteFaceTwo);
        request.setNumPieceIdentite(numPieceIdentite);
        request.setDateNaissance(dateNaissance);
        request.setAdresse(adresse);

        try {
            Client client = serviceAgent.updateClient(clientId, request);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        try {
            serviceAgent.deleteClient(clientId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
