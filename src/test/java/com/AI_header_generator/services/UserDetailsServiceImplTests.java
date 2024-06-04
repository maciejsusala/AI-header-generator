package com.AI_header_generator.services;

import com.AI_header_generator.models.User;
import com.AI_header_generator.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {
    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        UserDetails result = userDetailsService.loadUserByUsername("testUser");
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    public void testLoadUserByUsername_NotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("testUser"));
    }
}