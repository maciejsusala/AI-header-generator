package com.AI_header_generator.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundExceptionTests {

    @Test
    public void testUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        assertEquals("User not found", exception.getMessage());
    }
}