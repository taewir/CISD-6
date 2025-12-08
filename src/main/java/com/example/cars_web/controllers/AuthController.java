package com.example.cars_web.controllers;

import com.example.cars_web.models.AppUser;
import com.example.cars_web.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") AppUser user,
                           BindingResult result,
                           Model model) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            result.rejectValue("username", "error.user",
                    "Username already exists");
        }

        if (result.hasErrors()) {
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return "redirect:/login?registered";
    }
}
