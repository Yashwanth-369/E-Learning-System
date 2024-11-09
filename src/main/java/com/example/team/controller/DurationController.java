package com.example.team.controller;

import com.example.team.model.Duration;
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
    public ResponseEntity<String> addDuration(@RequestBody Duration duration) {
        String responseMessage = durationService.addDuration(duration);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    // Endpoint to get all durations
    @GetMapping
    public ResponseEntity<List<Duration>> getAllDurations() {
        List<Duration> durations = durationService.getAllDurations();
        return ResponseEntity.ok(durations);
    }

    // Endpoint to get a duration by ID
    @GetMapping("/{id}")
    public ResponseEntity<Duration> getDurationById(@PathVariable Long id) {
        Optional<Duration> duration = durationService.getDurationById(id);
        return duration.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Endpoint to update a duration by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDuration(@PathVariable Long id, @RequestBody Duration updatedDuration) {
        String responseMessage = durationService.updateDuration(id, updatedDuration);
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
    public ResponseEntity<List<Duration>> getDurationsByType(@RequestParam("type") String type) {
        List<Duration> durations = durationService.getDurationsByType(type);
        return ResponseEntity.ok(durations);
    }

    // Endpoint to get durations by total hours
    @GetMapping("/total-hours")
    public ResponseEntity<List<Duration>> getDurationsByTotalHours(@RequestParam("hours") String totalHours) {
        List<Duration> durations = durationService.getDurationsByTotalHours(totalHours);
        return ResponseEntity.ok(durations);
    }

    // Endpoint to get durations containing a specific keyword in the description
    @GetMapping("/description")
    public ResponseEntity<List<Duration>> getDurationsByDescriptionKeyword(@RequestParam("keyword") String keyword) {
        List<Duration> durations = durationService.getDurationsByDescriptionKeyword(keyword);
        return ResponseEntity.ok(durations);
    }
}
