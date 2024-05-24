package com.example.jibiapp;

import com.example.jibiapp.services.SmsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JibiAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JibiAppApplication.class, args);

        /*SmsService smsService = new SmsService("ACcba54ba666f876381149fdd1841b6784", "1632b4a1bfa070eddd765d5e851f5cfa", "+14143106250");
        System.out.println(smsService.formatPhoneNumber("+212651061415"));*/
    }

}
