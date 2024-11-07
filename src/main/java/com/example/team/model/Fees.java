package com.example.team.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fees")
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseFeeId;
    private String courseFeeAmount;
    private String courseFeeType;
    private String courseFeeDescription;
    private String courseFeeTotal;
    private String courseFeePayment;

    // Constructors, Getters, Setters
    public Fees() {}

    public Fees(String courseFeeAmount, String courseFeeType, String courseFeeDescription, String courseFeeTotal, String courseFeePayment) {
        this.courseFeeAmount = courseFeeAmount;
        this.courseFeeType = courseFeeType;
        this.courseFeeDescription = courseFeeDescription;
        this.courseFeeTotal = courseFeeTotal;
        this.courseFeePayment = courseFeePayment;
    }

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
