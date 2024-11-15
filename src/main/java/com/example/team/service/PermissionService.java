package com.example.team.service;

import com.example.team.dto.PermissionDTO;
import com.example.team.model.Permission;
import com.example.team.model.Role;
import com.example.team.repository.PermissionRepository;
import com.example.team.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public PermissionService(PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    public String addPermission(PermissionDTO permissionDTO) {
        Optional<Role> role = roleRepository.findById(permissionDTO.getRoleId());
        if (role.isPresent()) {
            Permission permission = new Permission(role.get(), permissionDTO.getPermissionTitle(),
                    permissionDTO.getPermissionModule(), permissionDTO.getPermissionDescription());
            permissionRepository.save(permission);
            return "Permission added successfully!";
        }
        return "Role not found!";
    }

    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PermissionDTO getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public String updatePermission(Long id, PermissionDTO permissionDTO) {
        Optional<Permission> existingPermission = permissionRepository.findById(id);
        if (existingPermission.isPresent()) {
            Permission permission = existingPermission.get();
            permission.setPermissionTitle(permissionDTO.getPermissionTitle());
            permission.setPermissionModule(permissionDTO.getPermissionModule());
            permission.setPermissionDescription(permissionDTO.getPermissionDescription());
            permissionRepository.save(permission);
            return "Permission updated successfully!";
        }
        return "Permission not found!";
    }

    public String deletePermission(Long id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
            return "Permission deleted successfully!";
        }
        return "Permission not found!";
    }

    private PermissionDTO convertToDTO(Permission permission) {
        return new PermissionDTO(
                permission.getPermissionId(),
                permission.getRole().getId(),
                permission.getPermissionTitle(),
                permission.getPermissionModule(),
                permission.getPermissionDescription()
        );
    }
}
