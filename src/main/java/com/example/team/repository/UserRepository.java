package com.example.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.team.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find a user by email
    Optional<User> findByEmail(String email);

    // Find a user by reset token
    Optional<User> findByResetToken(String resetToken);

    // Check if a user exists by email
    boolean existsByEmail(String email);
}
