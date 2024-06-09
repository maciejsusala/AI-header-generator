package com.AI_header_generator.models;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
    private String email;
    private String role;
}

