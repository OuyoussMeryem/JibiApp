package com.example.jibiapp.services.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
   Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String,String> valuesMap=new HashMap<>();
        valuesMap.put("cloud_name","dyf3hngei");
        valuesMap.put("api_key","125289619386444");
        valuesMap.put("api_secret","itDjcw08EZIY4FwccRY4R3wq0eg");
        cloudinary=new Cloudinary(valuesMap);
    }

    public Map uplaod(MultipartFile multipartFile)throws IOException {
        File file=convert(multipartFile);
        Map result=cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        if(!Files.deleteIfExists(file.toPath())){
            throw new IOException("Failed to delete temporary file: "+file.getAbsolutePath());
        }
        return result;
    }

    private File convert(MultipartFile multipartFile)throws IOException {
        File file=new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo=new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }

    public Map delete(String id)throws IOException{
        return cloudinary.uploader().destroy(id,ObjectUtils.emptyMap());
    }

}
