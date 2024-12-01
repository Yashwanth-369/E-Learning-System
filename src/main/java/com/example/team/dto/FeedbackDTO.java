package com.example.team.dto;

import java.util.List;

public class FeedbackDTO {

    private Integer rating; // User rating (e.g., 1-5)
    private String feedbackComments; // Comments provided by the user
    private List<String> favoriteFields; // Comma-separated list of favorite fields

    // Constructors
    public FeedbackDTO() {
    }

    public FeedbackDTO(Integer rating, String feedbackComments, List<String> favoriteFields) {
        this.rating = rating;
        this.feedbackComments = feedbackComments;
        this.favoriteFields = favoriteFields;
    }

    // Getters and Setters
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFeedbackComments() {
        return feedbackComments;
    }

    public void setFeedbackComments(String feedbackComments) {
        this.feedbackComments = feedbackComments;
    }

    public List<String> getFavoriteFields() {
        return favoriteFields;
    }

    public void setFavoriteFields(List<String> favoriteFields) {
        this.favoriteFields = favoriteFields;
    }
}
