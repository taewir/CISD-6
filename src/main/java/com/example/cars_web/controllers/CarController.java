package com.example.cars_web.controllers;

import com.example.cars_web.models.Car;
import com.example.cars_web.repositories.CarRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("car", new Car());
        return "form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        carRepository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("car", car);
        return "form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Car car,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        car.setId(id);
        carRepository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/cars";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String brand, Model model) {
        if (brand != null && !brand.isBlank()) {
            model.addAttribute("cars", carRepository.findByBrandContainingIgnoreCase(brand));
            model.addAttribute("searchBrand", brand);
        } else {
            model.addAttribute("cars", carRepository.findAll());
        }
        return "list";
    }
}