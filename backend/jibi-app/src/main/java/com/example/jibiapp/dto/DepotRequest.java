package com.example.jibiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepotRequest {
    private String numCarte;
    private String nomCarte;
    private LocalDate dateExpiration;
    private String codeCvv;
    private String typeCarte;
    private Double montantDepot;
}
