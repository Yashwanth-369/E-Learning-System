package com.example.team.service;

import com.example.team.dto.DurationDTO;
import com.example.team.model.Duration;
import com.example.team.repository.DurationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DurationService {

    private final DurationRepository durationRepository;

    public DurationService(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    // Method to add a new Duration
    public String addDuration(DurationDTO durationDTO) {
        Duration duration = mapToEntity(durationDTO);
        durationRepository.save(duration);
        return "Duration added successfully!";
    }

    // Method to get all durations
    public List<DurationDTO> getAllDurations() {
        return durationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Method to get a duration by its ID
    public Optional<DurationDTO> getDurationById(Long id) {
        return durationRepository.findById(id).map(this::mapToDTO);
    }

    // Method to update an existing duration by ID
    public String updateDuration(Long id, DurationDTO updatedDurationDTO) {
        Optional<Duration> durationOptional = durationRepository.findById(id);
        if (durationOptional.isPresent()) {
            Duration duration = durationOptional.get();
            duration.setDurationType(updatedDurationDTO.getDurationType());
            duration.setDurationDescription(updatedDurationDTO.getDurationDescription());
            duration.setDurationDuration(updatedDurationDTO.getDurationDuration());
            duration.setDurationTotalHours(updatedDurationDTO.getDurationTotalHours());
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
    public List<DurationDTO> getDurationsByType(String type) {
        return durationRepository.findByDurationType(type).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Method to find durations by total hours
    public List<DurationDTO> getDurationsByTotalHours(String totalHours) {
        return durationRepository.findByDurationTotalHours(totalHours).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Method to find durations containing a specific keyword in the description
    public List<DurationDTO> getDurationsByDescriptionKeyword(String keyword) {
        return durationRepository.findByDurationDescriptionContaining(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert Duration entity to DurationDTO
    private DurationDTO mapToDTO(Duration duration) {
        DurationDTO durationDTO = new DurationDTO();
        durationDTO.setDurationId(duration.getDurationId());
        durationDTO.setDurationType(duration.getDurationType());
        durationDTO.setDurationDescription(duration.getDurationDescription());
        durationDTO.setDurationDuration(duration.getDurationDuration());
        durationDTO.setDurationTotalHours(duration.getDurationTotalHours());
        return durationDTO;
    }

    // Helper method to convert DurationDTO to Duration entity
    private Duration mapToEntity(DurationDTO durationDTO) {
        Duration duration = new Duration();
        duration.setDurationId(durationDTO.getDurationId());
        duration.setDurationType(durationDTO.getDurationType());
        duration.setDurationDescription(durationDTO.getDurationDescription());
        duration.setDurationDuration(durationDTO.getDurationDuration());
        duration.setDurationTotalHours(durationDTO.getDurationTotalHours());
        return duration;
    }
}
