package com.AI_header_generator.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    private User user;

    @BeforeEach
    public void setup() {
        user = new User(1L, "username", "email@example.com", "ROLE_USER", "password");
    }

    @Test
    public void testGetId() {
        assertEquals(1L, user.getId());
    }

    @Test
    public void testGetUsername() {
        assertEquals("username", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetAuthority() {
        assertEquals("ROLE_USER", user.getAuthority());
    }

    @Test
    public void testGetEmail() {
        assertEquals("email@example.com", user.getEmail());
    }
}