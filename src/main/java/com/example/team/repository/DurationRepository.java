package com.example.team.repository;

import com.example.team.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DurationRepository extends JpaRepository<Duration, Long> {

    // Find all durations by a specific type
    List<Duration> findByDurationType(String durationType);

    // Find all durations that have a specific total hours value
    List<Duration> findByDurationTotalHours(String durationTotalHours);

    // Find all durations containing a specific keyword in their description
    List<Duration> findByDurationDescriptionContaining(String keyword);

    // Add additional custom queries as needed
}
