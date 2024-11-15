package com.example.team.controller;

import com.example.team.dto.FeesDTO;
import com.example.team.service.FeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeesController {

    private final FeesService feesService;

    public FeesController(FeesService feesService) {
        this.feesService = feesService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFees(@RequestBody FeesDTO feesDTO) {
        String responseMessage = feesService.addFees(feesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping
    public ResponseEntity<List<FeesDTO>> getAllFees() {
        List<FeesDTO> feesList = feesService.getAllFees();
        return ResponseEntity.ok(feesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeesDTO> getFeesById(@PathVariable Long id) {
        FeesDTO feesDTO = feesService.getFeesById(id);
        if (feesDTO != null) {
            return ResponseEntity.ok(feesDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFees(@PathVariable Long id, @RequestBody FeesDTO updatedFeesDTO) {
        String responseMessage = feesService.updateFees(id, updatedFeesDTO);
        if (responseMessage.equals("Fees updated successfully!")) {
            return ResponseEntity.ok(responseMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFees(@PathVariable Long id) {
        String responseMessage = feesService.deleteFees(id);
        if (responseMessage.equals("Fees deleted successfully!")) {
            return ResponseEntity.ok(responseMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }
}
