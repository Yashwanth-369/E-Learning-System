package com.example.team.service;

import com.example.team.model.Course;
import com.example.team.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Method to register a new course
    public String addCourse(Course course) {
        courseRepository.save(course);
        return "Course added successfully!";
    }

    // Method to get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Method to get a course by its ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Method to update course availability
    public String updateCourseAvailability(Long id, boolean isAvailable) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setAvailable(isAvailable);
            courseRepository.save(course);
            return "Course availability updated successfully!";
        }
        return "Course not found!";
    }
}
