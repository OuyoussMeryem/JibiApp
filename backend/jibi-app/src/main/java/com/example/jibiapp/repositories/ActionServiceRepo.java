package com.example.jibiapp.repositories;

import com.example.jibiapp.enums.EtatActionService;
import com.example.jibiapp.models.ActionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionServiceRepo extends JpaRepository<ActionService,Long> {
    List<ActionService> findByClientIdAndServiceAgenceIdAndEtat(Long clientId, Long agenceId, EtatActionService etat);
    List<ActionService> findByClientIdAndEtat(Long clientId, EtatActionService etat);
}
