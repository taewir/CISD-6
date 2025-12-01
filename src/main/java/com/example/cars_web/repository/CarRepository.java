package com.example.cars_web.repository;

import com.example.cars_web.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandContainingIgnoreCase(String brand);
}