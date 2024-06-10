package com.example.jibiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPaymentSuccessEmail(String to, Double montant) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Paiement Réussi");
        message.setText("Votre paiement de " + montant + " a été effectué avec succès. Merci d'avoir utilisé notre service.");
        mailSender.send(message);
    }

}
