package com.AI_header_generator.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTests {

    @Mock
    private JwtTokenProvider tokenProvider;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private String jwt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        jwt = Jwts.builder()
                .setSubject("username")
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(tokenProvider.validateToken(jwt)).thenReturn(true);
        when(tokenProvider.getUsernameFromJWT(jwt)).thenReturn("username");
        when(userDetailsService.loadUserByUsername("username")).thenReturn(new User("username", "password", Collections.emptyList()));
    }

    @Test
    public void testDoFilterInternalWithValidJwt() throws Exception {
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalWithInvalidJwt() throws Exception {
        when(tokenProvider.validateToken(jwt)).thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }
}