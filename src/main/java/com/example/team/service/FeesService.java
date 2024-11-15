package com.example.team.service;

import com.example.team.dto.FeesDTO;
import com.example.team.model.Fees;
import com.example.team.repository.FeesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeesService {

    private final FeesRepository feesRepository;

    public FeesService(FeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    public String addFees(FeesDTO feesDTO) {
        Fees fees = convertToEntity(feesDTO);
        feesRepository.save(fees);
        return "Fees added successfully!";
    }

    public List<FeesDTO> getAllFees() {
        return feesRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FeesDTO getFeesById(Long id) {
        Optional<Fees> fees = feesRepository.findById(id);
        return fees.map(this::convertToDTO).orElse(null);
    }

    public String updateFees(Long id, FeesDTO updatedFeesDTO) {
        if (feesRepository.existsById(id)) {
            Fees updatedFees = convertToEntity(updatedFeesDTO);
            updatedFees.setCourseFeeId(id);
            feesRepository.save(updatedFees);
            return "Fees updated successfully!";
        }
        return "Fees not found!";
    }

    public String deleteFees(Long id) {
        if (feesRepository.existsById(id)) {
            feesRepository.deleteById(id);
            return "Fees deleted successfully!";
        }
        return "Fees not found!";
    }

    // Utility methods for converting between entity and DTO
    private FeesDTO convertToDTO(Fees fees) {
        FeesDTO feesDTO = new FeesDTO();
        feesDTO.setCourseFeeId(fees.getCourseFeeId());
        feesDTO.setCourseFeeAmount(fees.getCourseFeeAmount());
        feesDTO.setCourseFeeType(fees.getCourseFeeType());
        feesDTO.setCourseFeeDescription(fees.getCourseFeeDescription());
        feesDTO.setCourseFeeTotal(fees.getCourseFeeTotal());
        feesDTO.setCourseFeePayment(fees.getCourseFeePayment());
        return feesDTO;
    }

    private Fees convertToEntity(FeesDTO feesDTO) {
        Fees fees = new Fees();
        fees.setCourseFeeAmount(feesDTO.getCourseFeeAmount());
        fees.setCourseFeeType(feesDTO.getCourseFeeType());
        fees.setCourseFeeDescription(feesDTO.getCourseFeeDescription());
        fees.setCourseFeeTotal(feesDTO.getCourseFeeTotal());
        fees.setCourseFeePayment(feesDTO.getCourseFeePayment());
        return fees;
    }
}
