package com.example.team.dto;

public class RegistrationDTO {
    
    private String fullName;

    private String email;

    private String password;

    private String address;

    private String phoneNo;

    private String course;

    // Constructors
    public RegistrationDTO() {
    }

    public RegistrationDTO(String fullName, String email, String password, String address, String phoneNo, String course) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNo = phoneNo;
        this.course = course;
    }


     // Validation method
     public boolean validateRegistrationDTO() {
        if (this.fullName == null || this.fullName.isEmpty()) {
            return false; // Full Name is required
        }
        if (this.email == null || !this.email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false; // Invalid email format
        }
        if (this.password == null || this.password.length() < 6) {
            return false; // Password must be at least 6 characters
        }
        if (this.phoneNo != null && !this.phoneNo.matches("\\d{10}")) {
            return false; // Optional phone number must be 10 digits
        }
        if (this.address == null || this.address.isEmpty()) {
            return false; // Address is required
        }
        if (this.course == null || this.course.isEmpty()) {
            return false; // Course is required
        }
        return true;
    }
   /*
    public boolean validateRegistrationDTO(RegistrationDTO dto) {
        if (dto.getFullName() == null || dto.getFullName().isEmpty()) {
            return false; // Full Name is required
        }
        if (dto.getEmail() == null || !dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false; // Invalid email format
        }
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            return false; // Password must be at least 6 characters
        }
        if (dto.getPhoneNo() != null && !dto.getPhoneNo().matches("\\d{10}")) {
            return false; // Optional phone number must be 10 digits
        }
        return true;
    }
     */

    // Getters and Setters
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
}
