package com.example.team.repository;

import com.example.team.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find a role by its name (e.g., "ADMIN", "USER")
    Optional<Role> findByName(String name);
}
