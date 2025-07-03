package com.springboot.autowiki.controller;

import com.springboot.autowiki.service.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Change proposal functionality", description = "Change approval and rejection.")
@RestController
@RequestMapping("/api")
public class ArticleController {
    public final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/approve")
    public ResponseEntity<String> approveArticle(@RequestParam("token") String token) {
        boolean approved = articleService.approveArticle(token);

        if (approved) {
            return ResponseEntity.ok("Change proposal successfully approved.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }

    @GetMapping("/deny")
    public ResponseEntity<String> denyArticle(@RequestParam("token") String token) {
        boolean denied = articleService.denyArticle(token);

        if (denied) {
            return ResponseEntity.ok("Change proposal successfully denied.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
}
