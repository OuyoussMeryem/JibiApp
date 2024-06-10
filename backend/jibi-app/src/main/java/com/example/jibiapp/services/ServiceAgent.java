package com.example.jibiapp.services;


import com.example.jibiapp.dto.CreateClientRequest;
import com.example.jibiapp.enums.Role;
import com.example.jibiapp.models.*;
import com.example.jibiapp.repositories.AgentRepo;
import com.example.jibiapp.responses.AuthenticationResponse;
import com.example.jibiapp.services.authService.JwtService;
import com.example.jibiapp.services.image.CloudinaryService;
import com.example.jibiapp.services.image.ServiceImage;
import com.example.jibiapp.utils.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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

    @Autowired
    private final JwtService jwtService;

    public Agent findByPhoneNumber(String phoneNumber){
        return agentRepo.findByTelephone(phoneNumber);
    }

    public Optional<Agent> findByAdresseEmail(String email){
        return agentRepo.findByEmail(email);
   }

    public Agent save(Agent agent){
        return agentRepo.save(agent);
    }

    public Optional<Agent> findAgentByid(Long id){ return agentRepo.findById(id);}

    public AuthenticationResponse createClient(Long agentId, CreateClientRequest request) {
        String username = RandomUtil.generateRandomUsername();
        String password = RandomUtil.generateRandomPassword();

        Optional<Client> existingAgent = serviceClient.findByAdresseEmail(request.getEmail());
        if (existingAgent.isPresent()) {
            throw new RuntimeException("Email is already in use");
        }
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
        Optional<Agent> optionalAgent = findAgentByid(agentId);

        String token;
        if (optionalAgent.isPresent()) {
            Agent agent = optionalAgent.get();
            Agence agence = agent.getAgence();
            client.setAgent(agent);
            agent.getClients().add(client);
            client = serviceClient.save(client);
            token = jwtService.generateToken(client);
            String message = "Bonjour ! Votre username est : " + username + " et votre mot de passe est : " + password;
            smsService.sendSms(request.getTelephone(), message);

        } else {
            throw new RuntimeException("Agence introuvable avec l'ID : " + agentId);
        }

        return new AuthenticationResponse(token);
    }



    public Agent modifierUsernameAndPassword(Long agentId,String nouveauUsername,String nouveauPassword){
        Optional<Agent> optionalAgent=agentRepo.findById(agentId);
        if(optionalAgent.isPresent()){
            Agent agent=optionalAgent.get();
            if (isUsernameUniqueForAgent(nouveauUsername)) {
                agent.setUsername(nouveauUsername);
                agent.setPassword(passwordEncoder.encode(nouveauPassword));
                return save(agent);
            } else {
                throw new RuntimeException("Le nouveau nom d'utilisateur doit être unique.");
            }
        }else {
            throw new RuntimeException("Agent introuvable avec l'ID : " + agentId);
        }
    }

    public Agent modifierPassword(Long agentId,String nouveauPassword){
        Optional<Agent> optionalAgent=agentRepo.findById(agentId);
        if(optionalAgent.isPresent()){
            Agent agent=optionalAgent.get();
            agent.setPassword(passwordEncoder.encode(nouveauPassword));
            return save(agent);
        }else {
            throw new RuntimeException("Agent introuvable avec l'ID : " + agentId);
        }
    }

    private boolean isUsernameUniqueForAgent(String username) {
        return !agentRepo.existsByUsername(username);
    }

    public void delete(Agent agent) {
        agentRepo.delete(agent);
    }

    public void deleteClient(Long clientId) {
        Optional<Client> client = serviceClient.findClientById(clientId);
        if (client.isPresent()) {
            serviceClient.delete(client.get());
        } else {
            throw new RuntimeException("Client not found");
        }
    }

    public Client updateClient(Long clientId, CreateClientRequest request) {
        Optional<Client> clientOptional = serviceClient.findClientById(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            if (!client.getEmail().equals(request.getEmail())) {
                Optional<Client> existingClient = serviceClient.findByAdresseEmail(request.getEmail());
                if (existingClient.isPresent()) {
                    throw new RuntimeException("Email is already in use");
                }
            }

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

            return serviceClient.save(client);
        } else {
            throw new RuntimeException("Client not found");
        }
    }

    public List<Agent> findAllAgents() {
        return agentRepo.findAll();
    }

    public List<Client> getAllClients() {
        return serviceClient.findAllClients();
    }
}
