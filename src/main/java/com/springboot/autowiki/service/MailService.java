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

    public void sendPasswordResetLink(User user) {
        String resetUrl = appProperties.getDomain() + "/api/password-reset/confirm?token=" + user.getPasswordResetToken();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Password Reset Request");
            helper.setText(
                    "<p>Hi " + user.getNickname() + ",</p>" +
                            "<p>To reset your password, please click the link below:</p>" +
                            "<p><a href=\"" + resetUrl + "\">Reset Password</a></p>" +
                            "<p>If you didn't request this, please ignore this email.</p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send password reset link", e);
        }
    }

    public void sendPasswordResetEmail(User user, String newPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Your New Password");
            helper.setText(
                    "<p>Hi " + user.getNickname() + ",</p>" +
                            "<p>Your password has been successfully reset.</p>" +
                            "<p><strong>New Password:</strong> " + newPassword + "</p>" +
                            "<p>Please change it after logging in.</p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send new password email", e);
        }
    }

    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }

    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    public String generateApprovalToken() {
        return UUID.randomUUID().toString();
    }

    public String generateDenialToken() {
        return UUID.randomUUID().toString();
    }
}
