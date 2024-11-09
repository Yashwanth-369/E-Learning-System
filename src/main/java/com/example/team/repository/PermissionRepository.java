package com.example.team.repository;

import com.example.team.model.Permission;
import com.example.team.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    // Find all permissions for a specific role
    List<Permission> findByRole(Role role);

    // Find permissions by title
    List<Permission> findByPermissionTitle(String permissionTitle);

    // Find permissions by module name
    List<Permission> findByPermissionModule(String permissionModule);

    // Optional: Find permissions by both title and module
    List<Permission> findByPermissionTitleAndPermissionModule(String permissionTitle, String permissionModule);
}
