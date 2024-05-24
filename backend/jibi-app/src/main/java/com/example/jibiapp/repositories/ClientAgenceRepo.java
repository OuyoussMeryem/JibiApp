package com.example.jibiapp.repositories;

import com.example.jibiapp.models.ClientAgence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAgenceRepo extends JpaRepository<ClientAgence,Long> {
}
