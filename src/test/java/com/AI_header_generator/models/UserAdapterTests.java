package com.AI_header_generator.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAdapterTests {

    private User user;
    private UserAdapter userAdapter;

    @BeforeEach
    public void setup() {
        user = Mockito.mock(User.class);
        userAdapter = new UserAdapter(user);
    }

    @Test
    public void testGetAuthorities() {
        Mockito.when(user.getAuthority()).thenReturn("ROLE_USER");
        Collection<? extends GrantedAuthority> authorities = userAdapter.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void testGetPassword() {
        Mockito.when(user.getPassword()).thenReturn("password");
        assertEquals("password", userAdapter.getPassword());
    }

    @Test
    public void testGetUsername() {
        Mockito.when(user.getUsername()).thenReturn("username");
        assertEquals("username", userAdapter.getUsername());
    }

    @Test
    public void testGetEmail() {
        Mockito.when(user.getEmail()).thenReturn("email@example.com");
        assertEquals("email@example.com", userAdapter.getEmail());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(userAdapter.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(userAdapter.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(userAdapter.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(userAdapter.isEnabled());
    }
}