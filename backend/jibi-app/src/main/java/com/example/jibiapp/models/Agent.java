package com.example.jibiapp.models;


import com.example.jibiapp.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Agent extends UserApp{
    @OneToOne
    private Image pieceIdentiteFaceOne;
    @OneToOne
    private Image pieceIdentiteFaceTwo;
    private String numPieceIdentite;
    private String dateNaissance;
    private String adresse;
    private String numPattente;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "agent",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Client> clients=new ArrayList<>();
    @ManyToOne
    private Agence agence;


    public Agent(){
        super();
        this.role=Role.AGENT;
    }

}
