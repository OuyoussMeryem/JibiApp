package com.example.jibiapp.services.authService;

import com.example.jibiapp.models.UserApp;
import com.example.jibiapp.repositories.UserAppRepo;
import com.example.jibiapp.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserAppRepo userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserApp request){
     return null;
    }

    public AuthenticationResponse authenticate(UserApp request){
    return null;

    }
}
