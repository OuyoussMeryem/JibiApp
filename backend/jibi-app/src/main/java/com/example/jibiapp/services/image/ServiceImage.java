package com.example.jibiapp.services.image;

import com.example.jibiapp.models.Image;
import com.example.jibiapp.repositories.ImageRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceImage {
    @Autowired
    private ImageRepo imageRepo;

    public List<Image> list(){
        return imageRepo.findByOrderById();
    }
    public Optional<Image> getOne(int id){
        return imageRepo.findById(id);
    }
    public void save(Image image){
        imageRepo.save(image);
    }
    public void delete(int id){
        imageRepo.deleteById(id);
    }
    public boolean exists(int id){
        return imageRepo.existsById(id);
    }



}
