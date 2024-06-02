package com.AI_header_generator.service;

import com.AI_header_generator.model.User;
import com.AI_header_generator.model.UserAdapter;
import com.AI_header_generator.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return new UserAdapter(user);
    }
}