package com.example.jibiapp.services.authService;

import com.example.jibiapp.repositories.UserAppRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private UserAppRepo userAppRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found !"));
    }
}
