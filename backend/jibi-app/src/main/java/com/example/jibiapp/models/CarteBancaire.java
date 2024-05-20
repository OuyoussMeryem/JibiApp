package com.example.jibiapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numCarte;
    private String nomCarte;
    private LocalDate dateExpiration;
    private String codeCvv;
    private String typeCarte; // Par exemple: "Visa", "MasterCard", etc.
    @OneToOne
    private Compte compte; // Assuming there's an Account class defined elsewhere

}
