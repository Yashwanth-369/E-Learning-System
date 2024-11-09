package com.example.team.repository;

import com.example.team.model.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Long> {

    // Find all fees by a specific type
    List<Fees> findByCourseFeeType(String courseFeeType);

    // Find all fees that have a specific total amount
    List<Fees> findByCourseFeeTotal(String courseFeeTotal);

    // Find all fees with a specific payment status
    List<Fees> findByCourseFeePayment(String courseFeePayment);

    // Additional custom query methods can be added as needed
}
