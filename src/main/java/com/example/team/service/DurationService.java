package com.example.team.service;

import com.example.team.model.Duration;
import com.example.team.repository.DurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DurationService {

    private final DurationRepository durationRepository;

    public DurationService(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    // Method to add a new Duration
    public String addDuration(Duration duration) {
        durationRepository.save(duration);
        return "Duration added successfully!";
    }

    // Method to get all durations
    public List<Duration> getAllDurations() {
        return durationRepository.findAll();
    }

    // Method to get a duration by its ID
    public Optional<Duration> getDurationById(Long id) {
        return durationRepository.findById(id);
    }

    // Method to update an existing duration by ID
    public String updateDuration(Long id, Duration updatedDuration) {
        Optional<Duration> durationOptional = durationRepository.findById(id);
        if (durationOptional.isPresent()) {
            Duration duration = durationOptional.get();
            duration.setDurationType(updatedDuration.getDurationType());
            duration.setDurationDescription(updatedDuration.getDurationDescription());
            duration.setDurationDuration(updatedDuration.getDurationDuration());
            duration.setDurationTotalHours(updatedDuration.getDurationTotalHours());
            durationRepository.save(duration);
            return "Duration updated successfully!";
        }
        return "Duration not found!";
    }

    // Method to delete a duration by ID
    public String deleteDuration(Long id) {
        if (durationRepository.existsById(id)) {
            durationRepository.deleteById(id);
            return "Duration deleted successfully!";
        }
        return "Duration not found!";
    }

    // Method to find durations by type
    public List<Duration> getDurationsByType(String type) {
        return durationRepository.findByDurationType(type);
    }

    // Method to find durations by total hours
    public List<Duration> getDurationsByTotalHours(String totalHours) {
        return durationRepository.findByDurationTotalHours(totalHours);
    }

    // Method to find durations containing a specific keyword in the description
    public List<Duration> getDurationsByDescriptionKeyword(String keyword) {
        return durationRepository.findByDurationDescriptionContaining(keyword);
    }
}
