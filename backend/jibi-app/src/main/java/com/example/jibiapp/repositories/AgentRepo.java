package com.example.jibiapp.repositories;

import com.example.jibiapp.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepo extends JpaRepository<Agent,Long> {
    Agent findByTelephone(String phoneNumber);
    Optional<Agent> findByEmail(String email);

    boolean existsByUsername(String username);
}
