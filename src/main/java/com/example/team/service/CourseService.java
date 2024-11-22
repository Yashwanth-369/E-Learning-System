package com.example.team.service;

import com.example.team.dto.CourseDTO;
import com.example.team.model.Course;
import com.example.team.repository.CourseRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;
    private final JavaMailSender mailSender;

    public CourseService(CourseRepository courseRepository, JavaMailSender mailSender) {
        this.courseRepository = courseRepository;
        this.mailSender = mailSender;
    }

    /**
     * Enroll a user in a course and send a confirmation email.
     *
     * @param courseId The ID of the course to enroll in.
     * @param email    The user's email address.
     * @return Enrollment status message.
     */
    public String enrollInCourse(Long courseId, String email) {
        logger.info("Attempting to enroll user with email: {} in course ID: {}", email, courseId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isEmpty()) {
            logger.warn("Course not found with ID: {}", courseId);
            return "Course not found!";
        }

        Course course = courseOptional.get();
        sendEnrollmentConfirmationEmail(email, course);
        logger.info("Enrollment successful for course ID: {}", courseId);
        return "Enrollment successful. Confirmation email sent!";
    }

    /**
     * Sends an enrollment confirmation email.
     *
     * @param email  The recipient's email address.
     * @param course The course details.
     */
    private void sendEnrollmentConfirmationEmail(String email, Course course) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Course Enrollment Confirmation");
        message.setText("Dear User,\n\nYou have successfully enrolled in the course: " +
                course.getCourseName() + " (" + course.getCourseType() + ").\n\n" +
                "Course Description: " + course.getCourseDescription() + "\n\n" +
                "Thank you for enrolling!\n\nBest regards,\nTeam");
        mailSender.send(message);
        logger.info("Enrollment confirmation email sent to: {}", email);
    }

    /**
     * Adds a new course to the system.
     *
     * @param courseDTO The course details.
     * @return Success message.
     */
    public String addCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        courseRepository.save(course);
        logger.info("New course added: {}", course.getCourseName());
        return "Course added successfully!";
    }

    /**
     * Fetches all courses as a list of DTOs.
     *
     * @return List of all courses.
     */
    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        logger.info("Fetched {} courses from the database.", courses.size());
        return courses;
    }

    /**
     * Fetches details of a course by ID.
     *
     * @param id The course ID.
     * @return Course details as DTO.
     */
    public CourseDTO getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isEmpty()) {
            logger.warn("Course not found with ID: {}", id);
            return null;
        }

        logger.info("Fetched details for course ID: {}", id);
        return convertToDTO(courseOptional.get());
    }

    /**
     * Updates the availability status of a course.
     *
     * @param id          The course ID.
     * @param isAvailable The availability status.
     * @return Status message.
     */
    public String updateCourseAvailability(Long id, boolean isAvailable) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isEmpty()) {
            logger.warn("Course not found with ID: {}", id);
            return "Course not found!";
        }

        Course course = courseOptional.get();
        course.setAvailable(isAvailable);
        courseRepository.save(course);
        logger.info("Updated availability for course ID: {} to {}", id, isAvailable);
        return "Course availability updated successfully!";
    }

    /**
     * Converts a CourseDTO to a Course entity.
     *
     * @param courseDTO The DTO to convert.
     * @return The corresponding Course entity.
     */
    private Course convertToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseType(courseDTO.getCourseType());
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseYear(courseDTO.getCourseYear());
        course.setAvailable(courseDTO.isAvailable());
        return course;
    }

    /**
     * Converts a Course entity to a CourseDTO.
     *
     * @param course The entity to convert.
     * @return The corresponding CourseDTO.
     */
    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId()); // Ensure courseId is included
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

import com.example.team.dto.CourseDTO;
import com.example.team.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.example.team.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    @Autowired
    private JavaMailSender mailSender;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public String enrollInCourse(Long courseId, String email) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            sendEnrollmentConfirmationEmail(email, course);
            return "Enrollment successful. Confirmation email sent!";
        }
        return "Course not found!";
    }


    private void sendEnrollmentConfirmationEmail(String email, Course course) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Course Enrollment Confirmation");
        message.setText("Dear User,\n\nYou have successfully enrolled in the course: " +
                course.getCourseName() + " (" + course.getCourseType() + ").\n\n" +
                "Course Description: " + course.getCourseDescription() + "\n\n" +
                "Thank you for enrolling!\n\nBest regards,\nTeam");
        mailSender.send(message);
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
        courseDTO.setCourseId(course.getCourseId()); 
        courseDTO.setCourseType(course.getCourseType());
        courseDTO.setCourseDescription(course.getCourseDescription());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseYear(course.getCourseYear());
        courseDTO.setAvailable(course.isAvailable());
        return courseDTO;
    }
}



*/