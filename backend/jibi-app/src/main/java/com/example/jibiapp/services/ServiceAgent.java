package com.example.jibiapp.services;


import com.example.jibiapp.dto.CreateClientRequest;
import com.example.jibiapp.enums.Role;
import com.example.jibiapp.models.Agent;
import com.example.jibiapp.models.Client;
import com.example.jibiapp.models.Image;
import com.example.jibiapp.repositories.AgentRepo;
import com.example.jibiapp.services.image.CloudinaryService;
import com.example.jibiapp.services.image.ServiceImage;
import com.example.jibiapp.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceAgent {
    @Autowired
    private AgentRepo agentRepo;
    @Autowired
    @Lazy
    private ServiceClient serviceClient;
    @Autowired
    private SmsService smsService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ServiceImage serviceImage;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Agent findByPhoneNumber(String phoneNumber){
        return agentRepo.findByTelephone(phoneNumber);
    }

    public Agent findByAdresseEmail(String email){
        return agentRepo.findByEmail(email);
   }

    public Agent save(Agent agent){
        return agentRepo.save(agent);
    }



    public Client createClient(Agent agent, CreateClientRequest request) {
        String username = RandomUtil.generateRandomUsername();
        String password = RandomUtil.generateRandomPassword();

        Client client = new Client();
        client.setNom(request.getNom());
        client.setPrenom(request.getPrenom());
        client.setEmail(request.getEmail());
        client.setTelephone(request.getTelephone());
        client.setNumPieceIdentite(request.getNumPieceIdentite());
        client.setDateNaissance(request.getDateNaissance());
        client.setAdresse(request.getAdresse());


        try {
            Map<String, Object> uploadResultOne = cloudinaryService.uplaod(request.getPieceIdentiteFaceOne());
            Image imageOne = new Image((String) uploadResultOne.get("original_filename"), (String) uploadResultOne.get("url"), (String) uploadResultOne.get("public_id"));
            serviceImage.save(imageOne);

            Map<String, Object> uploadResultTwo = cloudinaryService.uplaod(request.getPieceIdentiteFaceTwo());
            Image imageTwo = new Image((String) uploadResultTwo.get("original_filename"), (String) uploadResultTwo.get("url"), (String) uploadResultTwo.get("public_id"));
            serviceImage.save(imageTwo);

            client.setPieceIdentiteFaceOne(imageOne);
            client.setPieceIdentiteFaceTwo(imageTwo);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload images", e);
        }

        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setRole(Role.CLIENT);

        client = serviceClient.save(client);
        agent.getClients().add(client);

        String message = "Bonjour ! Votre username est : " + username + " et votre mot de passe est : " + password;
        smsService.sendSms(request.getTelephone(), message);

        return client;
    }

    public Agent modifierUsernameAndPassword(Long agentId,String nouveauUsername,String nouveauPassword){
        Optional<Agent> optionalAgent=agentRepo.findById(agentId);
        if(optionalAgent.isPresent()){
            Agent agent=optionalAgent.get();
            agent.setUsername(nouveauUsername);
            agent.setPassword(passwordEncoder.encode(nouveauPassword));
            return save(agent);

        }else {
            throw new RuntimeException("Agent introuvable avec l'ID : " + agentId);
        }
    }



}
