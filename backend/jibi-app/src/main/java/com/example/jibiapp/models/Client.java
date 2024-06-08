package com.example.jibiapp.models;


import com.example.jibiapp.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToOne(mappedBy = "client")
    private CompteApplication compteApplication;
    @OneToOne(mappedBy = "client")
    private CompteBancaireFictif compteBancaireFictif;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Paiment> paiments=new ArrayList<>();
    @ManyToOne
    private Agent agent;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ActionService>  actionService= new ArrayList<>();


    public Client() {
        super();
        this.setRole(Role.CLIENT);
    }


}
