package com.example.jibiapp.controllers;

import com.example.jibiapp.dto.DepotRequest;
import com.example.jibiapp.services.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/depot")
public class DepotController {
    @Autowired
    private DepotService depotService;

    @PostMapping("/{clientId}/effectuer")
    public ResponseEntity<String> effectuerDepot(@PathVariable Long clientId,
                                                 @RequestBody DepotRequest depotRequest) {
        boolean success = depotService.effectuerDepot(clientId, depotRequest);
        if (success) {
            return ResponseEntity.ok("Dépôt effectué avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec du dépôt.");
        }
    }
}
