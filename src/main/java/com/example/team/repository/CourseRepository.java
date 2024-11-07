package com.example.team.repository;

import com.example.team.model.Course;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Find courses by name (case-insensitive)

}
