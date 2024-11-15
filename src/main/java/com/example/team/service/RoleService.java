package com.example.team.service;

import com.example.team.model.Role;
import com.example.team.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public String addRole(Role role) {
        roleRepository.save(role);
        return "Role added successfully!";
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public String updateRole(Long id, Role updatedRole) {
        if (roleRepository.existsById(id)) {
            updatedRole.setId(id);
            roleRepository.save(updatedRole);
            return "Role updated successfully!";
        }
        return "Role not found!";
    }

    public String deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return "Role deleted successfully!";
        }
        return "Role not found!";
    }
}
