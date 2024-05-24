package com.example.jibiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DemandeIscriptionRequest {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private MultipartFile pieceIdentiteFaceOne;
    private MultipartFile pieceIdentiteFaceTwo;
    private String numPieceIdentite;
    private String dateNaissance;
    private String adresse;
}
