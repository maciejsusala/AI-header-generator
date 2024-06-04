package com.AI_header_generator.configuration;

import com.AI_header_generator.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.AI_header_generator.models.UserAdapter;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTokenProviderTests {

    private JwtTokenProvider jwtTokenProvider;
    private UserAdapter userAdapter;

    @BeforeEach
    public void setup() {
        jwtTokenProvider = new JwtTokenProvider();
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setAuthority("ROLE_USER");

        userAdapter = new UserAdapter(user);
    }

    @Test
    public void testGenerateToken() {
        String token = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(userAdapter, null, userAdapter.getAuthorities()));
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals("username", jwtTokenProvider.getUsernameFromJWT(token));
    }

    @Test
    public void testValidateToken() {
        String token = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(userAdapter, null, userAdapter.getAuthorities()));
        assertTrue(jwtTokenProvider.validateToken(token));

        String invalidToken = token.substring(1);
        assertFalse(jwtTokenProvider.validateToken(invalidToken));
    }
}