package com.example.jibiapp.services;

import com.example.jibiapp.dto.DepotRequest;
import com.example.jibiapp.models.CarteBancaire;
import com.example.jibiapp.models.CompteApplication;
import com.example.jibiapp.models.CompteBancaireFictif;
import com.example.jibiapp.repositories.CompteApplicationRepo;
import com.example.jibiapp.repositories.CompteBancaireFictifRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepotService {

    @Autowired
    private ServiceCompteBancaireFictif serviceCompteBancaireFictif;
    @Autowired
    private ServiceCompteApplication serviceCompteApplication;
    @Autowired
    private ServiceCMIService serviceCMIService;
    @Autowired
    private ServiceCarteBancaire serviceCarteBancaire;

    public boolean effectuerDepot(Long clientId, DepotRequest depotRequest) {
        Optional<CompteBancaireFictif> optionalCompteBancaireFictif = serviceCompteBancaireFictif.findCompteBancaireByClientId(clientId);
        Optional<CompteApplication> optionalCompteApplication = serviceCompteApplication.findCompteByClientId(clientId);

        if (optionalCompteBancaireFictif.isPresent() && optionalCompteApplication.isPresent()) {
            CompteBancaireFictif compteBancaireFictif = optionalCompteBancaireFictif.get();
            CompteApplication compteApplication = optionalCompteApplication.get();
            CarteBancaire carteBancaireAssociee = compteBancaireFictif.getCarteBancaire();

            CarteBancaire carteBancaireUtilisee = new CarteBancaire();
            carteBancaireUtilisee.setNumCarte(depotRequest.getNumCarte());
            carteBancaireUtilisee.setNomCarte(depotRequest.getNomCarte());
            carteBancaireUtilisee.setDateExpiration(depotRequest.getDateExpiration());
            carteBancaireUtilisee.setCodeCvv(depotRequest.getCodeCvv());
            carteBancaireUtilisee.setTypeCarte(depotRequest.getTypeCarte());

            if (serviceCMIService.verifierAutorisation(carteBancaireUtilisee,carteBancaireAssociee) && compteBancaireFictif.getSolde() >= depotRequest.getMontantDepot()) {
                compteBancaireFictif.setSolde(compteBancaireFictif.getSolde() - depotRequest.getMontantDepot());
                compteApplication.creditAccount(depotRequest.getMontantDepot());

                serviceCompteBancaireFictif.save(compteBancaireFictif);
                serviceCompteApplication.save(compteApplication);
                return true;
            }
        }
        return false;
    }
}

