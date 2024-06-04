package com.AI_header_generator.services;

import com.AI_header_generator.models.User;
import com.AI_header_generator.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTests {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.addUser(user);
        assertEquals(user, result);
    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<User> result = userService.findAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.updateUser(user);
        assertEquals(user, result);
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        Mockito.doNothing().when(userRepository).deleteById(id);
        userService.deleteUser(id);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
    }
}