package com.example.jibiapp.repositories;

import com.example.jibiapp.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Service,Long> {
}
