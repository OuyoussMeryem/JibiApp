package com.example.jibiapp.repositories;

import com.example.jibiapp.models.ActionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionServiceRepo extends JpaRepository<ActionService,Long> {


}
