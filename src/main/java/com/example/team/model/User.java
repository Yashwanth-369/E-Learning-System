package com.example.team.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "course")
    private String course;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "role")
    private String role;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "reset_token", unique = true)
    private String resetToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reset_token_expiry")
    private Date resetTokenExpiry;

    // Constructors 
    public User() {
    }

    public User(String fullName, String email, String password, String address, String phoneNo, String course, Date date, String role, boolean verified) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNo = phoneNo;
        this.course = course;
        this.date = date;
        this.role = role;
        this.verified = verified;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Date getResetTokenExpiry() {
        return resetTokenExpiry;
    }

    public void setResetTokenExpiry(Date resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }

  /* public static Object builder() {
        throw new UnsupportedOperationException("Unimplemented method 'builder'");
    }
   */ 
}
