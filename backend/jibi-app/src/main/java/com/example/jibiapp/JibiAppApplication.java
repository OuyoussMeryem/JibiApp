package com.example.jibiapp;

import com.example.jibiapp.enums.EtatActionService;
import com.example.jibiapp.enums.StatusTransaction;
import com.example.jibiapp.enums.TypeCompte;
import com.example.jibiapp.enums.serviceType;
import com.example.jibiapp.models.*;
import com.example.jibiapp.services.ServiceActionService;
import com.example.jibiapp.services.*;
import com.example.jibiapp.services.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JibiAppApplication {

    /*@Autowired
    private ServiceTransaction serviceTransaction;

    @Autowired
    private ServiceActionService serviceActionService;

    @Autowired
    private ServiceServices serviceServices;

    @Autowired
    private ServiceCMIService serviceCMIService;

    @Autowired
    private ServicePaiment servicePaiment;

    @Autowired
    private ServiceCompte serviceCompte; // Inject ServiceCompte
*/
    public static void main(String[] args) {
        SpringApplication.run(JibiAppApplication.class, args);
    }

   /* @Override
    public void run(String... args) throws Exception {
        // Testing getAllActionServices method
        System.out.println("All ActionServices:");
        serviceActionService.getAllActionServices().forEach(System.out::println);

        // Testing createActionService method
        ActionService actionService = new ActionService();
        // Set properties of actionService
        actionService.setMontant(100.0);
        // Set other properties as needed
        ActionService savedActionService = serviceActionService.createActionService(actionService);
        System.out.println("Created ActionService:");
        System.out.println(savedActionService);

        // Create a new service
        Services service = new Services();
        service.setId(1L);
        service.setName("Example Service");
        service.setType(serviceType.facture);
        service.setValable(true);

        // Save the service
        serviceServices.createService(service);

        // Retrieve all services
        List<Services> services = serviceServices.getAllServices();
        System.out.println("All Services:");
        for (Services s : services) {
            System.out.println(s);
        }

        CMIService newCMIService = new CMIService();
        newCMIService.setNom("Example CMIService");

        // Save the new CMIService
        CMIService savedCMIService = serviceCMIService.createCMIService(newCMIService);
        System.out.println("Created CMIService:");
        System.out.println(savedCMIService);

        // Retrieve all CMIServices
        List<CMIService> allCMIServices = serviceCMIService.getAllCMIServices();
        System.out.println("All CMIServices:");
        for (CMIService cmiService : allCMIServices) {
            System.out.println(cmiService);
        }

        // Create a new payment
        Paiment paiment = new Paiment();
        paiment.setMontant(500.0);
        paiment.setDatePaiment(LocalDateTime.now());

        // Save the payment
        Paiment savedPaiment = servicePaiment.createPaiment(paiment);
        System.out.println("Created Paiment:");
        System.out.println(savedPaiment);

        // Retrieve all payments
        List<Paiment> allPaiments = servicePaiment.getAllPaiments();
        System.out.println("All Paiments:");
        for (Paiment p : allPaiments) {
            System.out.println(p);
        }

        // Test Compte
        Compte newCompte = new Compte();
        newCompte.setNom("Example Compte");
        newCompte.setSolde(1000.0);
        newCompte.setType_compte(TypeCompte.Compte_200); // Set the type of the compte

        // Save the new Compte
        Compte savedCompte = serviceCompte.createCompte(newCompte);
        System.out.println("Created Compte:");
        System.out.println(savedCompte);

        // Retrieve all Comptes
        List<Compte> allComptes = serviceCompte.getAllComptes();
        System.out.println("All Comptes:");
        for (Compte compte : allComptes) {
            System.out.println(compte);
        }
    }*/
}
