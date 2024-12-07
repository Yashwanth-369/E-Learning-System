package com.example.team.service;

import com.example.team.dto.FeedbackDTO;
import com.example.team.model.Feedback;
import com.example.team.model.User;
import com.example.team.repository.FeedbackRepository;
import com.example.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Save feedback for a user identified by email
    public void saveFeedback(String userEmail, FeedbackDTO feedbackDTO) {
        // Finding the user by email
        User user = userRepository.findByEmail(userEmail)
                                   .orElseThrow(() -> new RuntimeException("User not found"));

        // Creating a new Feedback entity
        Feedback feedback = new Feedback();
        feedback.setUser(user);  // Associate the feedback with the user
        feedback.setRating(feedbackDTO.getRating());
        feedback.setFeedbackComments(feedbackDTO.getFeedbackComments());
        feedback.setFavoriteFields(feedbackDTO.getFavoriteFields()); // Single string for favorite fields
        
        System.out.println("Saving Feedback: " + feedback);

        // Saving the feedback to the repository
        feedbackRepository.save(feedback);
    }

    // Get feedback for a user identified by email
    public FeedbackDTO getFeedback(String userEmail) {
        // Finding the user by email
        User user = userRepository.findByEmail(userEmail)
                                   .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieving feedback for the user
        Feedback feedback = feedbackRepository.findByUser(user)
                                              .orElseThrow(() -> new RuntimeException("Feedback not found"));

        // Creating and returning the DTO with the feedback details
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setFeedbackComments(feedback.getFeedbackComments());
        feedbackDTO.setFavoriteFields(feedback.getFavoriteFields()); // Single string for favorite fields

        return feedbackDTO;
    }
}





/*package com.example.team.service;

import com.example.team.dto.FeedbackDTO;
import com.example.team.model.Feedback;
import com.example.team.model.User;
import com.example.team.repository.FeedbackRepository;
import com.example.team.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Save feedback for a user identified by email
    public void saveFeedback(String userEmail, FeedbackDTO feedbackDTO) {
        // Finding the user by email
        User user = userRepository.findByEmail(userEmail)
                                   .orElseThrow(() -> new RuntimeException("User not found"));

        // Creating a new Feedback entity
        Feedback feedback = new Feedback();
        feedback.setUser(user);  // Associate the feedback with the user
        feedback.setRating(feedbackDTO.getRating());
        feedback.setFeedbackComments(feedbackDTO.getFeedbackComments());
        feedback.setFavoriteFields(feedbackDTO.getFavoriteFields());
        
        System.out.println("Saving Feedback: " + feedback);

        // Saving the feedback to the repository
        feedbackRepository.save(feedback);
    }

    // Get feedback for a user identified by email
    public FeedbackDTO getFeedback(String userEmail) {
        // Finding the user by email
        User user = userRepository.findByEmail(userEmail)
                                   .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieving feedback for the user
        Feedback feedback = feedbackRepository.findByUser(user)
                                              .orElseThrow(() -> new RuntimeException("Feedback not found"));

        // Creating and returning the DTO with the feedback details
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setFeedbackComments(feedback.getFeedbackComments());
        feedbackDTO.setFavoriteFields(feedback.getFavoriteFields());

        return feedbackDTO;
    }
}


/*package com.example.team.service;

import com.example.team.dto.FeedbackDTO;
import com.example.team.model.Feedback;
import com.example.team.model.User;
import com.example.team.repository.FeedbackRepository;
import com.example.team.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Save feedback for a user identified by email
    public void saveFeedback(String userEmail, FeedbackDTO feedbackDTO) {
        // Finding the user by email
        User user = userRepository.findByEmail(userEmail)
                                   .orElseThrow(() -> new RuntimeException("User not found"));

        // Creating a new Feedback entity
        Feedback feedback = new Feedback();
        feedback.setUser(user);  // Associate the feedback with the user
        feedback.setRating(feedbackDTO.getRating());
        feedback.setFeedbackComments(feedbackDTO.getFeedbackComments());
        feedback.setFavoriteFields(feedbackDTO.getFavoriteFields());
        
        System.out.println("Saving Feedback: " + feedback);

        // Saving the feedback to the repository
        feedbackRepository.save(feedback);
    }

    // Get feedback for a user identified by email
    public FeedbackDTO getFeedback(String userEmail) {
         // Finding the user by email
         User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieving feedback for the user
         Feedback feedback = feedbackRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Feedback not found"));

        // Creating and returning the DTO with the feedback details
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setFeedbackComments(feedback.getFeedbackComments());
        feedbackDTO.setFavoriteFields(feedback.getFavoriteFields());

        return feedbackDTO;
    }
}

*/