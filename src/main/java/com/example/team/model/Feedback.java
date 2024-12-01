package com.example.team.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Many feedback entries can belong to one user

    @Column(name = "rating")
    private Integer rating;  // Rating (1-5)

    @Column(name = "feedback_comments", length = 500)
    private String feedbackComments;  // Feedback comments

    @ElementCollection
    @CollectionTable(name = "user_favorite_fields", joinColumns = @JoinColumn(name = "feedback_id", nullable = false))
    @Column(name = "favorite_field")
    private List<String> favoriteFields;  // List of favorite fields from the feedback

    // Constructors
    public Feedback() {}

    public Feedback(User user, Integer rating, String feedbackComments, List<String> favoriteFields) {
        this.user = user;
        this.rating = rating;
        this.feedbackComments = feedbackComments;
        this.favoriteFields = favoriteFields;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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


/*package com.example.team.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Many feedback entries can belong to one user
    
    @Column(name = "rating")
    private Integer rating;  // Star rating (1-5)

    @Column(name = "feedback_comments", length = 500)
    private String feedbackComments;  // Store feedback comments

    @ElementCollection
    @CollectionTable(name = "user_favorite_fields", joinColumns = @JoinColumn(name = "feedback_id"))
    @Column(name = "favorite_field")
    private List<String> favoriteFields;  // Store favorite fields as a collection

    // Constructors
    public Feedback() {}

    public Feedback(User user, Integer rating, String feedbackComments, List<String> favoriteFields) {
        this.user = user;
        this.rating = rating;
        this.feedbackComments = feedbackComments;
        this.favoriteFields = favoriteFields;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
*/