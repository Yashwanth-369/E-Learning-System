package com.example.team.controller;

import com.example.team.dto.FeedbackDTO;
import com.example.team.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Endpoint to save feedback for a user identified by email
    @PostMapping("/{userEmail}")
    public ResponseEntity<String> saveFeedback(@PathVariable String userEmail, @RequestBody FeedbackDTO feedbackDTO) {
        try {
            // Saving feedback using the service layer
            feedbackService.saveFeedback(userEmail, feedbackDTO);
            return ResponseEntity.ok("Feedback saved successfully!");
        } catch (RuntimeException ex) {
            // Handling errors like user not found
            return ResponseEntity.badRequest().body("Error saving feedback: " + ex.getMessage());
        }
    }

    // Endpoint to fetch feedback for a user by email
    @GetMapping("/{userEmail}")
    public ResponseEntity<FeedbackDTO> getFeedback(@PathVariable String userEmail) {
        try {
            // Fetch feedback using the service layer
            FeedbackDTO feedbackDTO = feedbackService.getFeedback(userEmail);
            return ResponseEntity.ok(feedbackDTO);
        } catch (RuntimeException ex) {
            // Handling errors like feedback not found
            return ResponseEntity.badRequest().body(null);
        }
    }
}





/*package com.example.team.controller;

import com.example.team.dto.FeedbackDTO;
import com.example.team.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Endpoint to save feedback by userEmail
    @PostMapping("/{userEmail}")
    public ResponseEntity<String> saveFeedback(@PathVariable String userEmail, @RequestBody FeedbackDTO feedbackDTO) {
        try {
            // Calling service to save feedback
            feedbackService.saveFeedback(userEmail, feedbackDTO);
            return ResponseEntity.ok("Feedback saved successfully!");
        } catch (RuntimeException ex) {
            // Handle case where user not found or any error
            return ResponseEntity.badRequest().body("Error saving feedback: " + ex.getMessage());
        }
    }
}
*/
