package com.example.team.repository;

import com.example.team.model.Role;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Find roles by name (e.g., ADMIN, USER)

}
