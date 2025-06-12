package com.springboot.autowiki.controller;

import com.springboot.autowiki.dto.ChangePasswordRequest;
import com.springboot.autowiki.dto.UserProfileResponse;
import com.springboot.autowiki.model.User;
import com.springboot.autowiki.repository.UserRepository;
import com.springboot.autowiki.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private final UserRepository userRepository;
    private final UserService userService;

    public ProfileController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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

    @Operation(summary = "Change user password",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                            @Valid @RequestBody ChangePasswordRequest request) {
        try {
            userService.changePassword(userDetails.getUsername(),
                    request.getCurrentPassword(),
                    request.getNewPassword());
            return ResponseEntity.ok("Password changed successfully.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}