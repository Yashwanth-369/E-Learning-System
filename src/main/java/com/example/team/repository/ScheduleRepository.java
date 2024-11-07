package com.example.team.repository;

import com.example.team.model.Schedule;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Find schedules for a specific date

}
