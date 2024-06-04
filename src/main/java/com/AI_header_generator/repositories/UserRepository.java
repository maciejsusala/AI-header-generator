package com.AI_header_generator.repositories;

import com.AI_header_generator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}