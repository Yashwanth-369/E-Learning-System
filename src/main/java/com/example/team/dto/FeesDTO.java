package com.example.team.dto;

public class FeesDTO {
    private Long courseFeeId;
    private String courseFeeAmount;
    private String courseFeeType;
    private String courseFeeDescription;
    private String courseFeeTotal;
    private String courseFeePayment;

    // Getters and Setters
    public Long getCourseFeeId() {
        return courseFeeId;
    }

    public void setCourseFeeId(Long courseFeeId) {
        this.courseFeeId = courseFeeId;
    }

    public String getCourseFeeAmount() {
        return courseFeeAmount;
    }

    public void setCourseFeeAmount(String courseFeeAmount) {
        this.courseFeeAmount = courseFeeAmount;
    }

    public String getCourseFeeType() {
        return courseFeeType;
    }

    public void setCourseFeeType(String courseFeeType) {
        this.courseFeeType = courseFeeType;
    }

    public String getCourseFeeDescription() {
        return courseFeeDescription;
    }

    public void setCourseFeeDescription(String courseFeeDescription) {
        this.courseFeeDescription = courseFeeDescription;
    }

    public String getCourseFeeTotal() {
        return courseFeeTotal;
    }

    public void setCourseFeeTotal(String courseFeeTotal) {
        this.courseFeeTotal = courseFeeTotal;
    }

    public String getCourseFeePayment() {
        return courseFeePayment;
    }

    public void setCourseFeePayment(String courseFeePayment) {
        this.courseFeePayment = courseFeePayment;
    }
}
