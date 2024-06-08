package com.example.jibiapp;

import com.example.jibiapp.services.SmsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JibiAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JibiAppApplication.class, args);
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "adminjibiapp";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);*/

    }

}
