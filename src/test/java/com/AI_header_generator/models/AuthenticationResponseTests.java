package com.AI_header_generator.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationResponseTests {

    private AuthenticationResponse authenticationResponse;

    @BeforeEach
    public void setup() {
        authenticationResponse = new AuthenticationResponse("jwtToken");
    }

    @Test
    public void testGetJwt() {
        assertEquals("jwtToken", authenticationResponse.getJwt());
    }

    @Test
    public void testSetJwt() {
        authenticationResponse.setJwt("newJwtToken");
        assertEquals("newJwtToken", authenticationResponse.getJwt());
    }
}