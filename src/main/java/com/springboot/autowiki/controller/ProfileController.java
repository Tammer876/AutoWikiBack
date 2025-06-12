package com.springboot.autowiki.controller;

import com.springboot.autowiki.dto.UserProfileResponse;
import com.springboot.autowiki.model.User;
import com.springboot.autowiki.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Tag(name = "User profile", description = "User profile operations")
    @Operation(summary = "Get current user profile",
               security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/me")
    public UserProfileResponse getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileResponse(user.getEmail(), user.getNickname(), user.getRole());
    }
}