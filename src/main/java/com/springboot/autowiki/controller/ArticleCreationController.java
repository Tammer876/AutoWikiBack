package com.springboot.autowiki.controller;

import com.springboot.autowiki.dto.ArticleCreationRequest;
import com.springboot.autowiki.model.Article;
import com.springboot.autowiki.model.User;
import com.springboot.autowiki.repository.UserRepository;
import com.springboot.autowiki.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Tag(name = "Change proposal creation", description = "Change proposal creation for the changelog.")
@RestController
@RequestMapping("/api")
public class ArticleCreationController {
    private final UserRepository userRepository;
    private final ArticleService articleService;

    public ArticleCreationController(UserRepository userRepository, ArticleService articleService) {
        this.userRepository = userRepository;
        this.articleService = articleService;
    }

    @Operation(summary = "Create new change proposal", description = "Creates a new change proposal inside the database." +
            "and sends emails to all enabled admin profiles for its consideration.", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/newarticle")
    public ResponseEntity<?> createNewArticle(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ArticleCreationRequest request) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Article createdArticle = articleService.createArticle(
                request.getCarId(),
                user.getEmail(),
                request.getProposedManufacturer(),
                request.getProposedModel(),
                request.getProposedGearType(),
                request.getProposedFuelType(),
                request.getProposedEngineDisplacement(),
                request.getProposedEnginePower(),
                request.getProposedEngineTorque(),
                request.getProposedProductionStartYear(),
                request.getProposedProductionEndYear(),
                request.getProposedPrice(),
                request.getProposedNumberOfSeats(),
                request.getProposedBodyType(),
                request.getProposedNumberOfDoors(),
                request.getProposedLength(),
                request.getProposedWidth(),
                request.getProposedHeight(),
                request.getProposedWheelbase(),
                request.getProposedWeight(),
                request.getProposedAccelerationToHundred(),
                request.getProposedTopSpeed(),
                request.getProposedDriveWheelsConfiguration(),
                request.getProposedWeightPerHP(),
                request.getProposedImageUrl()
        );

        return ResponseEntity.ok("Change proposal created successfully. Please wait for the administration to check it.");
    }
}
