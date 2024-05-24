package com.example.jibiapp.dto;

import com.example.jibiapp.models.Image;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Getter
@Setter
public class CreateAgentRequest {
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
