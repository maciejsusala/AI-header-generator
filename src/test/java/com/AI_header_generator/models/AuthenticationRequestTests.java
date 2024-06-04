package com.AI_header_generator.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationRequestTests {

    private AuthenticationRequest authenticationRequest;

    @BeforeEach
    public void setup() {
        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("username");
        authenticationRequest.setPassword("password");
        authenticationRequest.setEmail("email@example.com");
        authenticationRequest.setRole("ROLE_USER");
    }

    @Test
    public void testGetUsername() {
        assertEquals("username", authenticationRequest.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", authenticationRequest.getPassword());
    }

    @Test
    public void testGetEmail() {
        assertEquals("email@example.com", authenticationRequest.getEmail());
    }

    @Test
    public void testGetRole() {
        assertEquals("ROLE_USER", authenticationRequest.getRole());
    }
}