package com.example.jibiapp.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String responseType;
    public AuthenticationResponse(String responseType,String token) {
        this.responseType=responseType;
        this.token = token;
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }



}
