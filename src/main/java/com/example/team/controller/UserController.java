package com.example.team.controller;

import com.example.team.dto.*;
import com.example.team.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        String responseMessage = userService.registerUser(registrationDTO);
        if ("Registration successful!".equals(responseMessage)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp) {
        boolean isVerified = userService.verifyOtp(email, otp);
        if (isVerified) {
            return ResponseEntity.ok("OTP verified successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP or email.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = userService.authenticateUser(loginDTO);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<String> requestPasswordReset(@RequestBody EmailDTO emailDTO) {
        boolean isEmailSent = userService.sendPasswordResetEmail(emailDTO.getEmail());
        if (isEmailSent) {
            return ResponseEntity.ok("Password reset link sent to your email.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam("token") String token,
            @RequestParam("email") String email,
            @RequestBody String newPassword) {
        
        boolean isPasswordReset = userService.resetPassword(email, token, newPassword);
        if (isPasswordReset) {
            return ResponseEntity.ok("Password reset successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reset password. Invalid token or email.");
    }
}
/*package com.example.team.controller;

import com.example.team.dto.RegistrationDTO;
import com.example.team.dto.EmailDTO;
import com.example.team.dto.LoginDTO;
import com.example.team.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        String responseMessage = userService.registerUser(registrationDTO);
        if ("Registration successful!".equals(responseMessage)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = userService.authenticateUser(loginDTO);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully.");
    }

    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    boolean isDeleted = userService.deleteUserById(id);
    if (isDeleted) {
        return ResponseEntity.ok("User deleted successfully.");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
}


    @PostMapping("/reset-password-request")
    public ResponseEntity<String> requestPasswordReset(@RequestBody EmailDTO emailDTO) {
       boolean isEmailSent = userService.sendPasswordResetEmail(emailDTO.getEmail());
        if (isEmailSent) {
           return ResponseEntity.ok("Password reset link sent to your email.");
        }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address.");
    }


    // New method to handle password reset with token and new password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam("token") String token,
            @RequestParam("email") String email,
            @RequestBody String newPassword) {
        
        boolean isPasswordReset = userService.resetPassword(email, token, newPassword);
        if (isPasswordReset) {
            return ResponseEntity.ok("Password reset successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reset password. Invalid token or email.");
    }
}
*/