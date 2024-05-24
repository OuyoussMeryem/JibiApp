package com.example.jibiapp.models;


import com.example.jibiapp.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Client extends UserApp{
    @OneToOne
    private Image pieceIdentiteFaceOne;
    @OneToOne
    private Image pieceIdentiteFaceTwo;
    private String numPieceIdentite;
    private String dateNaissance;
    private String adresse;
    @OneToOne
    private Compte compte;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Paiment> paiments=new ArrayList<>();
    @ManyToOne
    private Agent agent;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ActionService>  actionService= new ArrayList<>();
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ClientAgence> clientAgences = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;

    public Client(){
        super();
        this.role= Role.CLIENT;
    }

}
