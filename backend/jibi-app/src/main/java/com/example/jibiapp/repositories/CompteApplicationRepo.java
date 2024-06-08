package com.example.jibiapp.repositories;

import com.example.jibiapp.models.CompteApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteApplicationRepo extends JpaRepository<CompteApplication,Long> {
    Optional<CompteApplication> findByClientId(Long clientId);
}
