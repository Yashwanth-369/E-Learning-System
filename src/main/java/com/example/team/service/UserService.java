package com.example.team.service;

import com.example.team.dto.RegistrationDTO;
import com.example.team.dto.LoginDTO;
import com.example.team.model.User;
import com.example.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${app.reset-password-url:http://localhost:8080/api/users/reset-password}")
    private String resetPasswordUrl;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    /**
     * Register a new user.
     */
    public String registerUser(RegistrationDTO registrationDTO) {
        if (!validateRegistrationDTO(registrationDTO)) {
            return "Invalid registration data. Please check the input fields.";
        }

        if (userRepository.findByEmail(registrationDTO.getEmail()).isPresent()) {
            return "Email is already in use.";
        }

        User user = new User(
            registrationDTO.getFullName(),
            registrationDTO.getEmail(),
            passwordEncoder.encode(registrationDTO.getPassword()),
            registrationDTO.getAddress(),
            registrationDTO.getPhoneNo(),
            registrationDTO.getCourse(),
            new Date(),
            "USER",
            false
        );

        System.out.println("Encoded password on registration: " + user.getPassword());
        userRepository.save(user);
        return "Registration successful!";
    }

    /**
     * Authenticate user by email and password.
     */
    public boolean authenticateUser(LoginDTO loginDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println("Login attempt - raw password: " + loginDTO.getPassword());
            System.out.println("Encoded password in database: " + user.getPassword());
            boolean passwordMatches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
            System.out.println("Password match result: " + passwordMatches);
            return passwordMatches;
        }
        System.out.println("No user found with email: " + loginDTO.getEmail());
        return false;
    }

    /**
     * Send a password reset email with a unique token.
     */
    public boolean sendPasswordResetEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            System.out.println("No user found with email: " + email);
            return false;
        }

        User user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);

        String resetLink = resetPasswordUrl + "?token=" + token + "&email=" + email;
        sendEmail(email, "Password Reset Request", "To reset your password, please click the link below:\n" + resetLink);

        System.out.println("Password reset email sent to: " + email);
        return true;
    }

    /**
     * Reset password using the provided token.
     */
    public boolean resetPassword(String email, String token, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (token.equals(user.getResetToken())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                System.out.println("Encoded password after reset: " + user.getPassword());
                user.setResetToken(null);  // Clear the reset token
                userRepository.save(user);
                System.out.println("Encoded password after reset: " + user.getPassword());
                System.out.println("Password reset successfully for email: " + email);
                return true;
            } else {
                System.out.println("Invalid reset token for email: " + email);
            }
        } else {
            System.out.println("No user found with email: " + email);
        }
        return false;
    }

    /**
     * Validate the registration data.
     */
    private boolean validateRegistrationDTO(RegistrationDTO dto) {
        return dto.getFullName() != null && !dto.getFullName().isEmpty()
                && dto.getEmail() != null && dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")
                && dto.getPassword() != null && dto.getPassword().length() >= 6
                && (dto.getPhoneNo() == null || dto.getPhoneNo().matches("\\d{10}"));
    }

    /**
     * Utility method to send an email.
     */
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
        System.out.println("Email sent to: " + to);
    }

    public boolean deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
