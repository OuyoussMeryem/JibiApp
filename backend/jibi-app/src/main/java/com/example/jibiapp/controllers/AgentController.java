package com.example.jibiapp.controllers;

import com.example.jibiapp.dto.CreateClientRequest;
import com.example.jibiapp.models.Agent;
import com.example.jibiapp.models.Client;
import com.example.jibiapp.services.ServiceAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private ServiceAgent serviceAgent;

    private Agent getCurrentAgent() {
        return new Agent();}
    @PostMapping("/createClient")
    public Client createClient(
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String telephone,
            @RequestParam MultipartFile pieceIdentiteFaceOne,
            @RequestParam MultipartFile pieceIdentiteFaceTwo,
            @RequestParam String numPieceIdentite,
            @RequestParam String dateNaissance,
            @RequestParam String adresse){

        Agent agent=getCurrentAgent();
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


        return serviceAgent.createClient(agent,request);
    }

  @PutMapping("/modifierUsernamePassword/{agentId}")
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

}
