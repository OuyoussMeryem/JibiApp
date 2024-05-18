package com.example.jibiapp.repositories;

import com.example.jibiapp.models.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteBancaireRepo extends JpaRepository<CarteBancaire,Long> {
}
