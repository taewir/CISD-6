package com.example.cars_web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Column(nullable = false)
    private String role;
}
