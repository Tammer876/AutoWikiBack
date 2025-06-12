package com.springboot.autowiki.service;

import com.springboot.autowiki.exception.UserAlreadyExistsException;
import com.springboot.autowiki.model.User;
import com.springboot.autowiki.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailService mailService;

    public UserService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String email, String rawPassword, String nickname, String role) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        if (userRepository.existsByNickname(nickname)) {
            throw new UserAlreadyExistsException("Nickname already in use");
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);
        String verificationToken = mailService.generateVerificationToken();

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setNickname(nickname);

        if (role == null || role.isEmpty()) {
            user.setRole("USER"); // Default role USER
        } else {
            user.setRole(role);
        }
        user.setEnabled(false); // Until email is verified email
        user.setVerificationToken(verificationToken);

        User savedUser = userRepository.save(user);
        mailService.sendVerificationEmail(savedUser);
        return savedUser;
    }

    public void resendVerificationEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with this email does not exist."));

        if (user.isEnabled()) {
            throw new RuntimeException("Account is already verified.");
        }

        String newToken = mailService.generateVerificationToken();
        user.setVerificationToken(newToken);
        userRepository.save(user);

        mailService.sendVerificationEmail(user);
    }

    public boolean activateUser(String token) {
        Optional<User> optionalUser = userRepository.findByVerificationToken(token);

        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        user.setEnabled(true);
        user.setVerificationToken(null);

        userRepository.save(user);
        return true;
    }

    public void changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}