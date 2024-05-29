package com.example.jibiapp.repositories;

import com.example.jibiapp.models.BackOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackOfficeRepo extends JpaRepository<BackOffice,Long> {
}