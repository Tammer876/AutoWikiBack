package com.springboot.autowiki.service;

import com.springboot.autowiki.model.User;
import com.springboot.autowiki.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Value("${app.domain}")
    private String appDomain;

    public PasswordResetService(UserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public void requestPasswordReset(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return;

        User user = userOpt.get();
        String token = UUID.randomUUID().toString();
        user.setPasswordResetToken(token);
        userRepository.save(user);

        String url = appDomain + "/api/reset-password?token=" + token;
        mailService.sendPasswordResetEmail(user, url);
    }

    @Transactional
    public void resetPassword(String token) {
        Optional<User> userOpt = userRepository.findByPasswordResetToken(token);
        if (userOpt.isEmpty()) throw new RuntimeException("Invalid token");

        User user = userOpt.get();
        String newPassword = generateSecurePassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        userRepository.save(user);

        mailService.sendPasswordResetEmail(user, newPassword);
    }

    private String generateSecurePassword() {
        return UUID.randomUUID().toString().substring(0, 12);
    }
}