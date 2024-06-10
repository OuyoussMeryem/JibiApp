package com.example.jibiapp.services;

import com.example.jibiapp.dto.DemandeIscriptionRequest;
import com.example.jibiapp.enums.EtatActionService;
import com.example.jibiapp.enums.StatusTransaction;
import com.example.jibiapp.models.*;
import com.example.jibiapp.repositories.*;
import com.example.jibiapp.services.image.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceClient {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    @Lazy
    private ServiceAgent serviceAgent;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ActionServiceRepo actionServiceRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ServiceCMIService cmiService;
    @Autowired
    private CompteApplicationRepo compteApplicationRepo;
    @Autowired
    private EmailService emailService;

    public void demandeInscription(DemandeIscriptionRequest client, String email) {
        Agent agent = serviceAgent.findByAdresseEmail(email).get();
        if (agent != null) {
            try {
                Image imageFaceOne = uploadImage(client.getPieceIdentiteFaceOne());
                Image imageFaceTwo = uploadImage(client.getPieceIdentiteFaceTwo());

                String emailContent = "<html><body>"
                        + "<p><strong>Informations du client :</strong></p>"
                        + "<p><strong>Nom:</strong> " + client.getNom() + "</p>"
                        + "<p><strong>Prénom:</strong> " + client.getPrenom() + "</p>"
                        + "<p><strong>Email:</strong> " + client.getEmail() + "</p>"
                        + "<p><strong>Téléphone:</strong> " + client.getTelephone() + "</p>"
                        + "<p><strong>Numéro de Pièce d'Identité:</strong> " + client.getNumPieceIdentite() + "</p>"
                        + "<p><strong>Date de Naissance:</strong> " + client.getDateNaissance() + "</p>"
                        + "<p><strong>Adresse:</strong> " + client.getAdresse() + "</p>"
                        + "<p><strong>Pièce d'Identité Face One:</strong> " + imageFaceOne.getImageUrl() + "</p>"
                        + "<p><strong>Pièce d'Identité Face Two:</strong> " + imageFaceTwo.getImageUrl() + "</p>"
                        + "</body></html>";

                sendHtmlEmail(agent.getEmail(), "Demande d'inscription d'un nouveau client", emailContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun agent trouvé avec l'email spécifié.");
        }
    }

    private Image uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinaryService.uplaod(file);
        Image image = new Image(
                file.getOriginalFilename(),
                uploadResult.get("url").toString(),
                uploadResult.get("public_id").toString()
        );
        imageRepo.save(image);
        return image;
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public Optional<Client> findClientById(Long id){
        return clientRepo.findById(id);
    }
    public Client save(Client client){
        return clientRepo.save(client);
    }

    public Client modifierUsernameAndPasswordForAgence(Long clientId, String nouveauUsername, String nouveauPassword) {
        Optional<Client> optionalClient = findClientById(clientId);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();


            if (isUsernameUniqueForAgence(nouveauUsername)) {
                client.setUsername(nouveauUsername);
                client.setPassword(passwordEncoder.encode(nouveauPassword));
                save(client);
                return client;
            } else {
                throw new RuntimeException("Le nouveau nom d'utilisateur doit être unique parmi les clients de l'agence.");
            }
        } else {
            throw new RuntimeException("Client ou agence introuvable avec l'ID : " + clientId);
        }
    }

    public Client modifierPassword(Long clientId, String nouveauPassword) {
        Optional<Client> optionalClient = findClientById(clientId);

        if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                client.setPassword(passwordEncoder.encode(nouveauPassword));
                save(client);
                return client;
        }else {
            throw new RuntimeException("client introuvable avec l'ID : " + clientId);
        }

    }
    public Optional<Client> findByAdresseEmail(String email){
        return clientRepo.findByEmail(email);
    }
    private boolean isUsernameUniqueForAgence(String username) {
        return !clientRepo.existsByUsername(username);
    }


    public void delete(Client client) {
        clientRepo.delete(client);
    }


    public List<Client> findAllClients() {
        return clientRepo.findAll();
    }

    public List<ActionService> getNonPayeeActionServicesByClientAndAgence(Long clientId, Long agenceId) {
        return actionServiceRepo.findByClientIdAndServiceAgenceIdAndEtat(clientId, agenceId, EtatActionService.NONPAYEE);
    }




    //paiment *********************************************
    public boolean payActionService(Long clientId, Long actionServiceId) {
        Optional<ActionService> actionServiceOpt = actionServiceRepo.findById(actionServiceId);
        if (actionServiceOpt.isEmpty()) {
            return false;
        }

        ActionService actionService = actionServiceOpt.get();
        if (!actionService.getClient().getId().equals(clientId) || actionService.getEtat() != EtatActionService.NONPAYEE) {
            return false;
        }

        Client client = actionService.getClient();
        CompteApplication compte = client.getCompteApplication();
        if (!compte.hasSufficientBalance(actionService.getMontant())) {
            return false;
        }

        Transaction transaction = new Transaction();
        transaction.setMontant(actionService.getMontant());
        transaction.setStatut(StatusTransaction.ENATTENTE);
        transaction.setDate(new java.util.Date());
        transaction.setCompte(compte);
        transaction.setActionService(actionService);
        transactionRepo.save(transaction);

        boolean transactionInitiated = cmiService.initierTransaction(transaction);
        if (!transactionInitiated) {
            transaction.échouer();
            transactionRepo.save(transaction);
            return false;
        }

        boolean transactionValidated = cmiService.validerTransaction(transaction);
        if (!transactionValidated) {
            transaction.échouer();
            transactionRepo.save(transaction);
            return false;
        }

        boolean transactionConfirmed = cmiService.confirmerTransaction(transaction);
        if (!transactionConfirmed) {
            transaction.échouer();
            transactionRepo.save(transaction);
            return false;
        }

        compte.debitAccount(actionService.getMontant());
        compteApplicationRepo.save(compte);

        actionService.payer();
        actionService.setDate(LocalDateTime.now());
        actionServiceRepo.save(actionService);

        transaction.confirmer();
        transactionRepo.save(transaction);

        emailService.sendPaymentSuccessEmail(client.getEmail(), actionService.getMontant());
        return true;
    }
    public List<ActionService> getPayeeActionServicesByClient(Long clientId) {
        return actionServiceRepo.findByClientIdAndEtat(clientId, EtatActionService.PAYEE);
    }

    public boolean VerifierAmountWithActionService(Long actionServiceId, Double amount) {
        ActionService actionService = actionServiceRepo.findById(actionServiceId).orElse(null);
        if (actionService == null) {
            return false;
        }
        return actionService.getMontant().equals(amount);
    }
}
