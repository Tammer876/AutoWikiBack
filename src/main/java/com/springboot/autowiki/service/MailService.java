package com.springboot.autowiki.service;

import com.springboot.autowiki.model.Article;
import com.springboot.autowiki.model.Car;
import com.springboot.autowiki.model.User;
import com.springboot.autowiki.config.AppProperties;
import com.springboot.autowiki.repository.CarRepository;
import com.springboot.autowiki.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MailService {
    private final AppProperties appProperties;
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public MailService(AppProperties appProperties, JavaMailSender mailSender,  UserRepository userRepository,  CarRepository carRepository) {
        this.appProperties = appProperties;
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
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

    public void sendArticleApprovalLink(Article article) {
        String approvalUrl = appProperties.getDomain() + "/api/approve?token=" + article.getApprovalToken();
        String denialUrl = appProperties.getDomain() + "/api/deny?token=" + article.getDenialToken();

        List<User> recipients = userRepository.findAllByRoleAndEnabled("ADMIN", true);
        String[] recipientEmails = recipients.stream().map(User::getEmail).toArray(String[]::new);
        Optional<Car> carToBeChanged = carRepository.findById(article.getCarId());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmails);
            helper.setSubject("New change proposal");
            helper.setText(
                    "<p>Hi Admins,</p>" +
                    "<p>There is a new change proposal to be considered.</p>" +
                    "<p>Here are the proposed changes compared to the original article:</p>" +
                    "<p>Model: " + carToBeChanged.orElseThrow().getModel() + " ————> " + article.getProposedModel() + "</p>" +
                    "<p>Manufacturer: " + carToBeChanged.orElseThrow().getManufacturer() + " ————> " + article.getProposedManufacturer() + "</p>" +
                    "<p>Production start year: " + carToBeChanged.orElseThrow().getProductionStartYear() + " ————> " + article.getProposedProductionStartYear() + "</p>" +
                    "<p>Production end year: " + carToBeChanged.orElseThrow().getProductionEndYear() + " ————> " + article.getProposedProductionEndYear() + "</p>" +
                    "<p>Price: " + carToBeChanged.orElseThrow().getPrice() + " ————> " + article.getProposedPrice() + "</p>" +
                    "<p>Number of seats: " + carToBeChanged.orElseThrow().getNumberOfSeats() + " ————> " + article.getProposedNumberOfSeats() + "</p>" +
                    "<p>Body type: " + carToBeChanged.orElseThrow().getBodyType() + " ————> " + article.getProposedBodyType() + "</p>" +
                    "<p>Number of doors: " + carToBeChanged.orElseThrow().getNumberOfDoors() + " ————> " + article.getProposedNumberOfDoors() + "</p>" +
                    "<p>Length: " + carToBeChanged.orElseThrow().getLength() + " ————> " + article.getProposedLength() + "</p>" +
                    "<p>Width: " + carToBeChanged.orElseThrow().getWidth() + " ————> " + article.getProposedWidth() + "</p>" +
                    "<p>Height: " + carToBeChanged.orElseThrow().getHeight() + " ————> " + article.getProposedHeight() + "</p>" +
                    "<p>Wheelbase: " + carToBeChanged.orElseThrow().getWheelbase() + " ————> " + article.getProposedWheelbase() + "</p>" +
                    "<p>Weight: " + carToBeChanged.orElseThrow().getWeight() + " ————> " + article.getProposedWeight() + "</p>" +
                    "<p>Engine Power: " + carToBeChanged.orElseThrow().getEnginePower() + " ————> " + article.getProposedEnginePower() + "</p>" +
                    "<p>EngineTorque: " + carToBeChanged.orElseThrow().getEngineTorque() + " ————> " + article.getProposedEngineTorque() + "</p>" +
                    "<p>Gear type: " + carToBeChanged.orElseThrow().getGearType() + " ————> " + article.getProposedGearType() + "</p>" +
                    "<p>Fuel type: " + carToBeChanged.orElseThrow().getFuelType() + " ————> " + article.getProposedFuelType() + "</p>" +
                    "<p>Engine displacement: " + carToBeChanged.orElseThrow().getEngineDisplacement() + " ————> " + article.getProposedEngineDisplacement() + "</p>" +
                    "<p>Acceleration to 100kph: " + carToBeChanged.orElseThrow().getAccelerationToHundred() + " ————> " + article.getProposedAccelerationToHundred() + "</p>" +
                    "<p>Top speed: " + carToBeChanged.orElseThrow().getTopSpeed() + " ————> " + article.getProposedTopSpeed() + "</p>" +
                    "<p>Drive wheels configuration: " + carToBeChanged.orElseThrow().getDriveWheelsConfiguration() + " ————> " + article.getProposedDriveWheelsConfiguration() + "</p>" +
                    "<p>Weight per horsepower: " + carToBeChanged.orElseThrow().getWeightPerHP() + " ————> " + article.getProposedWeightPerHP() + "</p>" +
                    "<p>Image url: " + carToBeChanged.orElseThrow().getImageUrl() + " ————> " + article.getProposedImageUrl() + "</p>" +
                    "<p>To approve this change, click <a href=\"" + approvalUrl + "\">approve</a>, if not — click <a href=\"" + denialUrl + "\">deny</a>.</p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send change proposal email.", e);
        }
    }

    public void sendChangeApprovalEmail(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Change proposal consideration");
            helper.setText(
                    "<p>Hi " + user.getNickname() + ",</p>" +
                        "<p>Upon careful consideration, we decided to approve the changes you proposed. The edited version is already available on the website.</p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send change approval email.", e);
        }
    }

    public void sendChangeRejectionEmail(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Change proposal consideration");
            helper.setText(
                    "<p>Hi " + user.getNickname() + ",</p>" +
                    "<p>Upon careful consideration, we, unfortunately, decided to reject the changes you proposed. They will not be applied to the article on AutoWiki.</p>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send change rejection email.", e);
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
