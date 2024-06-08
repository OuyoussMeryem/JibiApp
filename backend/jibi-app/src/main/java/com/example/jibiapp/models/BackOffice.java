package com.example.jibiapp.models;

import com.example.jibiapp.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
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
public class BackOffice extends UserApp{
    @OneToMany
    @JsonManagedReference
    private List<Agent> agents=new ArrayList<>();

    public BackOffice() {
        super();
        this.setRole(Role.ADMIN);
    }

}
