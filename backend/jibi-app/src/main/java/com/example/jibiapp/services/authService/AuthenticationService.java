package com.example.jibiapp.services.authService;

import com.example.jibiapp.models.UserApp;
import com.example.jibiapp.repositories.UserAppRepo;
import com.example.jibiapp.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserAppRepo userRepository;
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(UserApp request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserApp user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.isFirstAuth()) {
            user.setFirstAuth(false);
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse("redirect", token);
        } else {

            String token = jwtService.generateToken(user);
            return new AuthenticationResponse("authenticationSuccessful", token);
        }
    }


}
