package com.example.team.dto;

public class DurationDTO {
    private Long durationId;
    private String durationType;
    private String durationDescription;
    private String durationDuration;
    private String durationTotalHours;

    // Getters and Setters
    public Long getDurationId() {
        return durationId;
    }

    public void setDurationId(Long durationId) {
        this.durationId = durationId;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public String getDurationDescription() {
        return durationDescription;
    }

    public void setDurationDescription(String durationDescription) {
        this.durationDescription = durationDescription;
    }

    public String getDurationDuration() {
        return durationDuration;
    }

    public void setDurationDuration(String durationDuration) {
        this.durationDuration = durationDuration;
    }

    public String getDurationTotalHours() {
        return durationTotalHours;
    }

    public void setDurationTotalHours(String durationTotalHours) {
        this.durationTotalHours = durationTotalHours;
    }
}
