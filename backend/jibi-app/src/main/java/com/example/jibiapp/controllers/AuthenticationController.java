package com.example.jibiapp.controllers;

import com.example.jibiapp.models.UserApp;
import com.example.jibiapp.responses.AuthenticationResponse;
import com.example.jibiapp.services.authService.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserApp request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
