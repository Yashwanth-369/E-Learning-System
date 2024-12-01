package com.example.team.repository;

import com.example.team.model.Feedback;
import com.example.team.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Find feedback by user
    Optional<Feedback> findByUser(User user);
}
