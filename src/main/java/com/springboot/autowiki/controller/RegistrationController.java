package com.springboot.autowiki.controller;

import com.springboot.autowiki.dto.RegisterRequest;
import com.springboot.autowiki.model.User;
import com.springboot.autowiki.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Registration", description = "User registration and account creation")

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account with email, password and nickname")
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        User createdUser = userService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                request.getRole()
        );

        return ResponseEntity.ok("User registered successfully. Please check your email.");
    }
}