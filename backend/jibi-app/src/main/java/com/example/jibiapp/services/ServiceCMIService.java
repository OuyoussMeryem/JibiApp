package com.example.jibiapp.services;

import com.example.jibiapp.models.CarteBancaire;
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
}
