package com.springboot.autowiki.service;

import com.springboot.autowiki.model.User;
import com.springboot.autowiki.config.AppProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService {
    private final AppProperties appProperties;
    private final JavaMailSender mailSender;

    public MailService(AppProperties appProperties, JavaMailSender mailSender) {
        this.appProperties = appProperties;
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(User user) {
        String verificationUrl = appProperties.getDomain() + "/api/verify?token=" + user.getVerificationToken();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Email Verification for AutoWiki");
            helper.setText(
                    "<p>Hi " + user.getNickname() + ",</p>" +
                            "<p>Please verify your email by clicking the link below:</p>" +
                            "<p><a href=\"" + verificationUrl + "\">Verify Email</a></p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
