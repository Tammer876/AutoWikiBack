package com.springboot.autowiki.controller;

import com.springboot.autowiki.dto.RegisterRequest;
import com.springboot.autowiki.dto.ResendVerificationRequest;
import com.springboot.autowiki.model.User;
import com.springboot.autowiki.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Registration", description = "User registration and account creation")

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account with email, password and nickname")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        User createdUser = userService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                request.getRole()
        );

        return ResponseEntity.ok("User registered successfully. Please check your email.");
    }

    @Operation(summary = "Resend verification email", description = "Sends a new verification email to the user")
    @PostMapping("/resend-verification")
    public ResponseEntity<?> resendVerification(
            @Valid @RequestBody ResendVerificationRequest request) {
        try {
            userService.resendVerificationEmail(request.getEmail());
            return ResponseEntity.ok("Verification email sent.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}