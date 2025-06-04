package com.springboot.autowiki.controller;

import com.springboot.autowiki.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User activation", description = "User activation and email verification operations")
@RestController
@RequestMapping("/api")
public class VerificationController {

    private final UserService userService;

    public VerificationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        boolean activated = userService.activateUser(token);

        if (activated) {
            return ResponseEntity.ok("Account successfully verified.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
}