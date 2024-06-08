package com.example.jibiapp.services;

import com.example.jibiapp.dto.CreateAgenceRequest;
import com.example.jibiapp.dto.CreateAgentRequest;
import com.example.jibiapp.enums.Role;
import com.example.jibiapp.models.Agence;
import com.example.jibiapp.models.Agent;
import com.example.jibiapp.models.BackOffice;
import com.example.jibiapp.models.Image;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceBackOffice {

    @Autowired
    @Lazy
    private ServiceAgent serviceAgent;
    @Autowired
    @Lazy
    private ServiceAgence serviceAgence;
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

    public AuthenticationResponse createAgent(BackOffice backOffice, CreateAgentRequest request) {
        String username = RandomUtil.generateRandomUsername();
        String password = RandomUtil.generateRandomPassword();

        Optional<Agent> existingAgent = serviceAgent.findByAdresseEmail(request.getEmail());
        if (existingAgent.isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        Agent agent = new Agent();
        agent.setNom(request.getNom());
        agent.setPrenom(request.getPrenom());
        agent.setEmail(request.getEmail());
        agent.setTelephone(request.getTelephone());
        agent.setNumPieceIdentite(request.getNumPieceIdentite());
        agent.setDateNaissance(request.getDateNaissance());
        agent.setAdresse(request.getAdresse());
        agent.setNumPattente(request.getNumPattente());

        try {
            Map<String, Object> uploadResultOne = cloudinaryService.uplaod(request.getPieceIdentiteFaceOne());
            Image imageOne = new Image((String) uploadResultOne.get("original_filename"), (String) uploadResultOne.get("url"), (String) uploadResultOne.get("public_id"));
            serviceImage.save(imageOne);

            Map<String, Object> uploadResultTwo = cloudinaryService.uplaod(request.getPieceIdentiteFaceTwo());
            Image imageTwo = new Image((String) uploadResultTwo.get("original_filename"), (String) uploadResultTwo.get("url"), (String) uploadResultTwo.get("public_id"));
            serviceImage.save(imageTwo);

            agent.setPieceIdentiteFaceOne(imageOne);
            agent.setPieceIdentiteFaceTwo(imageTwo);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload images", e);
        }

        agent.setUsername(username);
        agent.setPassword(passwordEncoder.encode(password));
        agent.setRole(Role.AGENT);
        agent = serviceAgent.save(agent);
        backOffice.getAgents().add(agent);
        String token = jwtService.generateToken(agent);
        String message = "Bonjour ! Votre username est : " + username + " et votre mot de passe est : " + password;
        smsService.sendSms(request.getTelephone(), message);

        return new AuthenticationResponse( token);
    }

    public Agence createAgence(CreateAgenceRequest request) {
        Agence agence = new Agence();
        agence.setNom(request.getNom());


        try {
            Map<String, Object> uploadResultOne = cloudinaryService.uplaod(request.getPieceIdentiteFaceOne());
            Image image = new Image((String) uploadResultOne.get("original_filename"), (String) uploadResultOne.get("url"), (String) uploadResultOne.get("public_id"));
            serviceImage.save(image);
            agence.setImage(image);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload images", e);
        }

        return  serviceAgence.save(agence);
    }


    public List<Agence> getAllAgences() {
        return serviceAgence.getAllAgence();
    }

    public void deleteAgent(Long agentId) {
        Optional<Agent> agent = serviceAgent.findAgentByid(agentId);
        if (agent.isPresent()) {
            serviceAgent.delete(agent.get());
        } else {
            throw new RuntimeException("Agent not found");
        }
    }

    public Agent updateAgent(Long agentId, CreateAgentRequest request) {
        Optional<Agent> agentOptional = serviceAgent.findAgentByid(agentId);
        if (agentOptional.isPresent()) {
            Agent agent = agentOptional.get();

            if (!agent.getEmail().equals(request.getEmail())) {
                Optional<Agent> existingAgent = serviceAgent.findByAdresseEmail(request.getEmail());
                if (existingAgent.isPresent()) {
                    throw new RuntimeException("Email is already in use");
                }
            }

            agent.setNom(request.getNom());
            agent.setPrenom(request.getPrenom());
            agent.setEmail(request.getEmail());
            agent.setTelephone(request.getTelephone());
            agent.setNumPieceIdentite(request.getNumPieceIdentite());
            agent.setDateNaissance(request.getDateNaissance());
            agent.setAdresse(request.getAdresse());
            agent.setNumPattente(request.getNumPattente());

            try {
                Map<String, Object> uploadResultOne = cloudinaryService.uplaod(request.getPieceIdentiteFaceOne());
                Image imageOne = new Image((String) uploadResultOne.get("original_filename"), (String) uploadResultOne.get("url"), (String) uploadResultOne.get("public_id"));
                serviceImage.save(imageOne);

                Map<String, Object> uploadResultTwo = cloudinaryService.uplaod(request.getPieceIdentiteFaceTwo());
                Image imageTwo = new Image((String) uploadResultTwo.get("original_filename"), (String) uploadResultTwo.get("url"), (String) uploadResultTwo.get("public_id"));
                serviceImage.save(imageTwo);

                agent.setPieceIdentiteFaceOne(imageOne);
                agent.setPieceIdentiteFaceTwo(imageTwo);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to upload images", e);
            }

            return serviceAgent.save(agent);
        } else {
            throw new RuntimeException("Agent not found");
        }
    }

    public List<Agent> getAllAgents() {
        return serviceAgent.findAllAgents();
    }


}
