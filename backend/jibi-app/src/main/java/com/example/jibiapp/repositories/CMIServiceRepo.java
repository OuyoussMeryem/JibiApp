package com.example.jibiapp.repositories;

import com.example.jibiapp.models.ActionService;
import com.example.jibiapp.models.CMIService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CMIServiceRepo extends JpaRepository<CMIService,Long> {
    CMIService findByTransactions_ActionService(ActionService actionService);

}
