package com.springboot.autowiki.service;

import com.springboot.autowiki.model.User;
import com.springboot.autowiki.config.AppProperties;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService {
    private final AppProperties appProperties;

    public MailService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public void sendVerificationEmail(User user) {
        String verificationUrl = appProperties.getDomain() + "/api/verify?token=" + user.getVerificationToken();

        System.out.println("📧 Sending verification email to: " + user.getEmail());
        System.out.println("🔗 Verification link: " + verificationUrl);
        System.out.println("📝 Nickname: " + user.getNickname());
    }

    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
