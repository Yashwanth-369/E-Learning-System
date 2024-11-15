package com.example.team.repository;

import com.example.team.model.Permission;
import com.example.team.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findByRole(Role role);

    List<Permission> findByPermissionTitle(String permissionTitle);

    List<Permission> findByPermissionModule(String permissionModule);

    List<Permission> findByPermissionTitleAndPermissionModule(String permissionTitle, String permissionModule);
}
