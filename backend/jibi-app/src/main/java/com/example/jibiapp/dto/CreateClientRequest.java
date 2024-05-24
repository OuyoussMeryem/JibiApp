package com.example.jibiapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateClientRequest {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private MultipartFile pieceIdentiteFaceOne;
    private MultipartFile pieceIdentiteFaceTwo;
    private String numPieceIdentite;
    private String dateNaissance;
    private String adresse;
    private String numPattente;
}
