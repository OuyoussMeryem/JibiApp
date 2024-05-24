package com.example.jibiapp.services;

import com.example.jibiapp.dto.CreateAgentRequest;
import com.example.jibiapp.enums.Role;
import com.example.jibiapp.models.Agent;
import com.example.jibiapp.models.BackOffice;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ServiceBackOffice {

    @Autowired
    @Lazy
    private ServiceAgent serviceAgent;
    @Autowired
    private SmsService smsService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ServiceImage serviceImage;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Agent createAgent(BackOffice backOffice, CreateAgentRequest request) {
        String username = RandomUtil.generateRandomUsername();
        String password = RandomUtil.generateRandomPassword();

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

        String message = "Bonjour ! Votre username est : " + username + " et votre mot de passe est : " + password;
        smsService.sendSms(request.getTelephone(), message);

        return agent;
    }
}
