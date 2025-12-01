package com.example.cars_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand cannot be empty")
    @Size(min = 2, max = 30, message = "Brand must be between 2 and 30 characters")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotBlank(message = "Color cannot be empty")
    private String color;

    @Min(value = 1886, message = "Year cannot be earlier than 1886")
    @Max(value = 2026, message = "Year cannot be in the future")
    private int year;

    @Positive(message = "Price must be greater than 0")
    private double price;
}