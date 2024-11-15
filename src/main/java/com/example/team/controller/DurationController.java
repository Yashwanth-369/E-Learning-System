package com.example.team.controller;

import com.example.team.dto.DurationDTO;
import com.example.team.service.DurationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/durations")
public class DurationController {

    private final DurationService durationService;

    public DurationController(DurationService durationService) {
        this.durationService = durationService;
    }

    // Endpoint to add a new duration
    @PostMapping("/add")
    public ResponseEntity<String> addDuration(@RequestBody DurationDTO durationDTO) {
        String responseMessage = durationService.addDuration(durationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    // Endpoint to get all durations
    @GetMapping
    public ResponseEntity<List<DurationDTO>> getAllDurations() {
        List<DurationDTO> durations = durationService.getAllDurations();
        return ResponseEntity.ok(durations);
    }

    // Endpoint to get a duration by ID
    @GetMapping("/{id}")
    public ResponseEntity<DurationDTO> getDurationById(@PathVariable Long id) {
        Optional<DurationDTO> duration = durationService.getDurationById(id);
        return duration.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Endpoint to update a duration by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDuration(@PathVariable Long id, @RequestBody DurationDTO updatedDurationDTO) {
        String responseMessage = durationService.updateDuration(id, updatedDurationDTO);
        if ("Duration not found!".equals(responseMessage)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to delete a duration by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDuration(@PathVariable Long id) {
        String responseMessage = durationService.deleteDuration(id);
        if ("Duration not found!".equals(responseMessage)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to get durations by type
    @GetMapping("/type")
    public ResponseEntity<List<DurationDTO>> getDurationsByType(@RequestParam("type") String type) {
        List<DurationDTO> durations = durationService.getDurationsByType(type);
        return ResponseEntity.ok(durations);
    }

    // Endpoint to get durations by total hours
    @GetMapping("/total-hours")
    public ResponseEntity<List<DurationDTO>> getDurationsByTotalHours(@RequestParam("hours") String totalHours) {
        List<DurationDTO> durations = durationService.getDurationsByTotalHours(totalHours);
        return ResponseEntity.ok(durations);
    }

    // Endpoint to get durations containing a specific keyword in the description
    @GetMapping("/description")
    public ResponseEntity<List<DurationDTO>> getDurationsByDescriptionKeyword(@RequestParam("keyword") String keyword) {
        List<DurationDTO> durations = durationService.getDurationsByDescriptionKeyword(keyword);
        return ResponseEntity.ok(durations);
    }
}
