package com.example.jibiapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Setter
@Getter
public class CreateAgenceRequest {
    private String nom;
    private MultipartFile pieceIdentiteFaceOne;
}
