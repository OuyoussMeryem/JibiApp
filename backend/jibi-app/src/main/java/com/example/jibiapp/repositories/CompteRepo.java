package com.example.jibiapp.repositories;

import com.example.jibiapp.enums.TypeCompte;
import com.example.jibiapp.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompteRepo extends JpaRepository<Compte, Long> {

    Compte findById(long id);
    List<Compte> getCompteById(long userId);

    @Query("SELECT c.type_compte FROM Compte c WHERE c.id = :accountId")
    TypeCompte findTypeCompteById(@Param("accountId") Long accountId);

    @Query("SELECT c.solde FROM Compte c WHERE c.id = :id")
    Double getCompteSolde(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Compte c SET c.solde = :newSolde WHERE c.id = :id")
    void changeSoldeCompte(@Param("newSolde") double newSolde, @Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Compte (id, nom, solde, type_compte) VALUES (:id, :nom, :solde, :type_compte)", nativeQuery = true)
    void createCompte(@Param("id") long id,
                      @Param("nom") String nom,
                      @Param("solde") double solde,
                      @Param("type_compte") TypeCompte typeCompte);
}
