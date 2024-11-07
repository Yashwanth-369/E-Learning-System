package com.example.team.repository;

import com.example.team.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DurationRepository extends JpaRepository<Duration, Long> {
}
