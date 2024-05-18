package com.example.jibiapp.repositories;

import com.example.jibiapp.models.Paiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaimentRepo extends JpaRepository<Paiment,Long> {
}
