package com.example.jibiapp.config;

import com.example.jibiapp.filter.JwtAuthenticationFilter;
import com.example.jibiapp.services.authService.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final UserDetailsImpl userDetailsImpl;
    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        req-> req.requestMatchers("/api/auth/login/**","/api/transactions/**","/api/BackOffice/agences"
                                        ,"/api/client/demandeInscription/**",
                                        "/api/agence/**",
                                        "/api/client/valableServicesByAgence/**").permitAll()
                                .requestMatchers("/api/client/**","/api/auth/logout/**").hasAnyAuthority("CLIENT")
                                .requestMatchers("/api/agent/**","/api/auth/logout/**").hasAnyAuthority("AGENT")
                                .requestMatchers("/api/depot/**").hasAnyAuthority("CLIENT")
                                .requestMatchers("/api/BackOffice/**","/api/auth/logout/**").hasAnyAuthority("ADMIN")
                                .anyRequest().authenticated()
                ).userDetailsService(userDetailsImpl)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();


    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
