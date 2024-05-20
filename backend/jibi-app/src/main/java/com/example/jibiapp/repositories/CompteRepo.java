package com.example.jibiapp.repositories;

import com.example.jibiapp.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepo extends JpaRepository<Compte,Long> {
}
