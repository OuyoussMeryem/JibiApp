package com.example.jibiapp.repositories;


import com.example.jibiapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {

    boolean existsByUsername(String username);
    Optional<Client> findByEmail(String email);
}
