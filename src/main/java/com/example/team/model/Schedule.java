package com.example.team.model;

import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String scheduleName;
    private String scheduleType;
    private String scheduleDescription;

    // Constructors, Getters, Setters
    public Schedule() {}

    public Schedule(String scheduleName, String scheduleType, String scheduleDescription) {
        this.scheduleName = scheduleName;
        this.scheduleType = scheduleType;
        this.scheduleDescription = scheduleDescription;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }
}
