package com.example.jibiapp.repositories;

import com.example.jibiapp.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepo extends JpaRepository<UserApp,Long> {
    Optional<UserApp> findByUsername(String username);
}
