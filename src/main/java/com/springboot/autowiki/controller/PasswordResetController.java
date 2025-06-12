package com.springboot.autowiki.controller;

import com.springboot.autowiki.service.PasswordResetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/request-password-reset")
    public void requestReset(@RequestParam String email) {
        passwordResetService.requestPasswordReset(email);
    }

    @GetMapping("/reset-password")
    public void resetPassword(@RequestParam String token) {
        passwordResetService.resetPassword(token);
    }
}