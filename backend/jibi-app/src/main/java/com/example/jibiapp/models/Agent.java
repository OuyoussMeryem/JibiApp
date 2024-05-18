package com.example.jibiapp.models;


import com.example.jibiapp.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Agent extends UserApp{

    private String pieceIdentite;
    private String numPieceIdentite;
    private Date dateNaissance;
    private String adresse;
    private String numPattente;
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
