package com.example.jibiapp.models;


import com.example.jibiapp.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "agent",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Client> clients=new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Agence agence;

    public Agent() {
        super();
        this.setRole(Role.AGENT);
    }

}
