package com.example.jibiapp.repositories;

import com.example.jibiapp.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepo extends JpaRepository<Image,Integer> {
   List<Image> findByOrderById();
}
