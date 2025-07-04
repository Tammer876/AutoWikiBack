package com.springboot.autowiki.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ResendVerificationRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}