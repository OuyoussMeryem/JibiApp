package com.example.jibiapp.models;

import com.example.jibiapp.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class BackOffice extends UserApp{
    @OneToMany
    private List<Agent> agents=new ArrayList<>();
    private Role role;
    public BackOffice(){
        super();
        this.role= Role.ADMIN;
    }
}
