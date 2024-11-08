package com.example.team.repository;

import com.example.team.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Find courses by name (case-insensitive)
        // Find courses by course name
        List<Course> findByCourseName(String courseName);

        // Find courses by availability status
        List<Course> findByAvailable(boolean available);

}
