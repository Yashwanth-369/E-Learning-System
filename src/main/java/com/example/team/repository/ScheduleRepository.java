package com.example.team.repository;

import com.example.team.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Find schedules by schedule type
    List<Schedule> findByScheduleType(String scheduleType);

    // Find schedules by schedule name
    List<Schedule> findByScheduleName(String scheduleName);
}
