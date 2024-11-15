package com.example.team.controller;

import com.example.team.dto.CourseDTO;
import com.example.team.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Endpoint to add a new course
    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody CourseDTO courseDTO) {
        String responseMessage = courseService.addCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    // Endpoint to get all courses
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Endpoint to get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id);
        if (courseDTO != null) {
            return ResponseEntity.ok(courseDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Endpoint to update course availability (available or unavailable)
    @PatchMapping("/{id}/availability")
    public ResponseEntity<String> updateCourseAvailability(
            @PathVariable Long id,
            @RequestParam("available") boolean available) {
        String responseMessage = courseService.updateCourseAvailability(id, available);
        if ("Course not found!".equals(responseMessage)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        return ResponseEntity.ok(responseMessage);
    }
}



/*package com.example.team.controller;

import com.example.team.model.Course;
import com.example.team.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Endpoint to add a new course
    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        String responseMessage = courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    // Endpoint to get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Endpoint to get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Endpoint to update course availability (available or unavailable)
    @PatchMapping("/{id}/availability")
    public ResponseEntity<String> updateCourseAvailability(
            @PathVariable Long id,
            @RequestParam("available") boolean available) {
        String responseMessage = courseService.updateCourseAvailability(id, available);
        if ("Course not found!".equals(responseMessage)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        return ResponseEntity.ok(responseMessage);
    }
}
*/