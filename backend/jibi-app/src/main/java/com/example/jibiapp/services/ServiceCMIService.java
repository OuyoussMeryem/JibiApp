package com.example.jibiapp.services;

import com.example.jibiapp.models.CarteBancaire;
import com.example.jibiapp.models.Client;
import org.springframework.stereotype.Service;

@Service
public class ServiceCMIService {

    public boolean verifierAutorisation(CarteBancaire carteBancaireUtilisee, CarteBancaire carteBancaireAssociee) {
        return carteBancaireUtilisee.getNumCarte().equals(carteBancaireAssociee.getNumCarte()) &&
                carteBancaireUtilisee.getNomCarte().equals(carteBancaireAssociee.getNomCarte()) &&
                carteBancaireUtilisee.getDateExpiration().equals(carteBancaireAssociee.getDateExpiration()) &&
                carteBancaireUtilisee.getCodeCvv().equals(carteBancaireAssociee.getCodeCvv()) &&
                carteBancaireUtilisee.getTypeCarte().equals(carteBancaireAssociee.getTypeCarte());
    }

    public boolean verifierInformationsClient(Client client, String nom, String prenom, String telephone, String email) {
        return client.getNom().equals(nom) &&
                client.getPrenom().equals(prenom) &&
                client.getTelephone().equals(telephone) &&
                client.getEmail().equals(email);
    }
}
