package com.example.jibiapp.repositories;

import com.example.jibiapp.models.CompteApplication;
import com.example.jibiapp.models.CompteBancaireFictif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteBancaireFictifRepo extends JpaRepository<CompteBancaireFictif,Long> {
    Optional<CompteBancaireFictif> findByClientId(Long clientId);
}
