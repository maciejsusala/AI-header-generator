package com.AI_header_generator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String authority;
    @Column(unique = true)
    private String email;


    public User() {
    }

    public User(Long id, String username, String email, String authority, String password) {
        this.id = id;
        this.username = username;
        this.authority = "ROLE_USER";
        this.password = password;
        this.email = email;
    }


}
