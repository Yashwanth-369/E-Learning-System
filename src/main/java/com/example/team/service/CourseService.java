package com.example.team.service;

import com.example.team.dto.CourseDTO;
import com.example.team.model.Course;
import com.example.team.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Method to register a new course
    public String addCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        courseRepository.save(course);
        return "Course added successfully!";
    }

    // Method to get all courses
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Method to get a course by its ID
    public CourseDTO getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(this::convertToDTO).orElse(null);
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

    private Course convertToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseType(courseDTO.getCourseType());
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseYear(courseDTO.getCourseYear());
        course.setAvailable(courseDTO.isAvailable());
        return course;
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseType(course.getCourseType());
        courseDTO.setCourseDescription(course.getCourseDescription());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseYear(course.getCourseYear());
        courseDTO.setAvailable(course.isAvailable());
        return courseDTO;
    }
}




/*
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
*/