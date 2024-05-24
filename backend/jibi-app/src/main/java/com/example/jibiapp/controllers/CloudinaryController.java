package com.example.jibiapp.controllers;

import com.example.jibiapp.models.Image;
import com.example.jibiapp.services.image.CloudinaryService;
import com.example.jibiapp.services.image.ServiceImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cloudinary")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ServiceImage imageService;

    @GetMapping("/listImages")
    public ResponseEntity<List<Image>> list(){
        List<Image>images=imageService.list();
        return new ResponseEntity<>(images, HttpStatus.OK);
        //return ResponseEntity.ok(images);
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public ResponseEntity<String> upload(@RequestParam MultipartFile multipartFile)throws IOException {
        BufferedImage bi= ImageIO.read(multipartFile.getInputStream());
        if(bi==null){
            return new ResponseEntity<>("Image non valide !",HttpStatus.BAD_REQUEST);
        }
        Map result=cloudinaryService.uplaod(multipartFile);
        Image image=new Image((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        imageService.save(image);
        return new ResponseEntity<>("image ajoutée avec success",HttpStatus.OK);
    }

    @DeleteMapping("/deleteImage/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id)throws IOException{
        Optional<Image> imageOptional=imageService.getOne(id);
        if(imageOptional.isEmpty()){
            return new ResponseEntity<>("image n'existe pas ",HttpStatus.NOT_FOUND);
        }
        Image image=imageOptional.get();
        String cloudinaryImageId=image.getImageId();
        try {
            cloudinaryService.delete(cloudinaryImageId);
        }catch (IOException e){
            return new ResponseEntity<>("Failed to delete image from cloudinary",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        imageService.delete(id);
        return new ResponseEntity<>("image supprimee",HttpStatus.OK);
    }

}
