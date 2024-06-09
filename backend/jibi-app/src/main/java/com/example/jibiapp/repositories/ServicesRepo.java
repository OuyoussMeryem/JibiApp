package com.example.jibiapp.repositories;

import com.example.jibiapp.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepo extends JpaRepository<Services,Long> {
}