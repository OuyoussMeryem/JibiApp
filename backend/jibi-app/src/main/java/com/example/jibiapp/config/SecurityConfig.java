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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final UserDetailsImpl userDetailsImpl;
    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


/*@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                    req-> req.requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/client/**").hasAnyAuthority("CLIENT")
                            .requestMatchers("/api/depot/**").hasAnyAuthority("CLIENT")
                            .requestMatchers("/api/agent/**").hasAnyAuthority("AGENT")
                            .requestMatchers("/api/BackOffice/**").hasAnyAuthority("ADMIN")
                            .anyRequest().authenticated()
               ).userDetailsService(userDetailsImpl)
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();


}*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        req-> req.requestMatchers("/api/auth/login/**","/api/BackOffice/**").permitAll()
                                .requestMatchers("/api/agent/**").hasAnyAuthority("AGENT")
                ).userDetailsService(userDetailsImpl)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();


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
