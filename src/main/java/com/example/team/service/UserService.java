package com.example.team.service;

import com.example.team.dto.RegistrationDTO;
import com.example.team.dto.LoginDTO;
import com.example.team.model.User;
import com.example.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${app.reset-password-url:http://localhost:8080/api/users/reset-password}")
    private String resetPasswordUrl;

    // Using ConcurrentHashMap for thread-safety (though it's not strictly necessary in this example)
    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    public String registerUser(RegistrationDTO registrationDTO) {
        if (!isValidRegistrationData(registrationDTO)) {
            return "Invalid registration data. Please check the input fields.";
        }

        Optional<User> existingUser = userRepository.findByEmail(registrationDTO.getEmail());
        if (existingUser.isPresent()) {
            return "Email is already in use.";
        }

        User newUser = createUserFromDto(registrationDTO);
        userRepository.save(newUser);
        sendOtpToEmail(registrationDTO.getEmail());

        return "Registration successful! Please verify your email using the OTP sent.";
    }

    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            optionalUser.ifPresent(user -> {
                user.setVerified(true);
                userRepository.save(user);
            });
            otpStorage.remove(email); // Clear OTP after verification
            return true;
        }
        return false;
    }

    private void sendOtpToEmail(String email) {
        String otp = generateOtp();
        otpStorage.put(email, otp);
        sendEmail(email, "OTP Verification", "Your OTP is: " + otp);
    }

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000)); // Generate 6-digit OTP
    }

    public boolean authenticateUser(LoginDTO loginDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.isVerified()) {
                return false;
            }
            System.out.println(loginDTO.getPassword()+"login password and user password "+user.getPassword());
            return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        }
        return false;
    }

    public boolean sendPasswordResetEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);

        String resetLink = resetPasswordUrl + "?token=" + token + "&email=" + email;
        sendEmail(email, "Password Reset Request", "To reset your password, please click the link below:\n" + resetLink);
        return true;
    }

    public boolean resetPassword(String email, String token, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (token.equals(user.getResetToken())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetToken(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private boolean isValidRegistrationData(RegistrationDTO dto) {
        return dto.getFullName() != null && !dto.getFullName().isEmpty()
                && dto.getEmail() != null && dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")
                && dto.getPassword() != null && dto.getPassword().length() >= 6
                && (dto.getPhoneNo() == null || dto.getPhoneNo().matches("\\d{10}"));
    }

    private User createUserFromDto(RegistrationDTO registrationDTO) {
        return new User(
                registrationDTO.getFullName(),
                registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getAddress(),
                registrationDTO.getPhoneNo(),
                registrationDTO.getCourse(),
                new Date(),
                "student", // Default role
                false // Verified status
        );
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }

    public boolean deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String getUserEmail(String email) {
        // Use Optional to retrieve the user
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getEmail(); // Extract and return the email
        }
        // Throw an exception if the user is not found
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
    
}


/*package com.example.team.service;

import com.example.team.dto.RegistrationDTO;
import com.example.team.dto.LoginDTO;
import com.example.team.model.User;
import com.example.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${app.reset-password-url:http://localhost:8080/api/users/reset-password}")
    private String resetPasswordUrl;

    private final Map<String, String> otpStorage = new HashMap<>();

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

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

        userRepository.save(user);
        sendOtpToEmail(registrationDTO.getEmail());
        return "Registration successful! Please verify your email using the OTP sent.";
    }

    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setVerified(true);
                userRepository.save(user);
                otpStorage.remove(email); // Clear OTP after verification
                return true;
            }
        }
        return false;
    }

    private void sendOtpToEmail(String email) {
        String otp = generateOtp();
        otpStorage.put(email, otp);

        sendEmail(email, "OTP Verification", "Your OTP is: " + otp);
        System.out.println("OTP sent to email: " + email + " | OTP: " + otp);
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate 6-digit OTP
        return String.valueOf(otp);
    }

    public boolean authenticateUser(LoginDTO loginDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.isVerified()) {
                System.out.println("User is not verified. Email: " + loginDTO.getEmail());
                return false;
            }
            return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        }
        return false;
    }

    public boolean sendPasswordResetEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);

        String resetLink = resetPasswordUrl + "?token=" + token + "&email=" + email;
        sendEmail(email, "Password Reset Request", "To reset your password, please click the link below:\n" + resetLink);
        return true;
    }

    public boolean resetPassword(String email, String token, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (token.equals(user.getResetToken())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetToken(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private boolean validateRegistrationDTO(RegistrationDTO dto) {
        return dto.getFullName() != null && !dto.getFullName().isEmpty()
                && dto.getEmail() != null && dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")
                && dto.getPassword() != null && dto.getPassword().length() >= 6
                && (dto.getPhoneNo() == null || dto.getPhoneNo().matches("\\d{10}"));
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
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

    
    private boolean validateRegistrationDTO(RegistrationDTO dto) {
        return dto.getFullName() != null && !dto.getFullName().isEmpty()
                && dto.getEmail() != null && dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")
                && dto.getPassword() != null && dto.getPassword().length() >= 6
                && (dto.getPhoneNo() == null || dto.getPhoneNo().matches("\\d{10}"));
    }

   
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
*/