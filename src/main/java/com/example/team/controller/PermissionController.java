package com.example.team.controller;

import com.example.team.dto.PermissionDTO;
import com.example.team.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPermission(@RequestBody PermissionDTO permissionDTO) {
        String responseMessage = permissionService.addPermission(permissionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        List<PermissionDTO> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable Long id) {
        PermissionDTO permission = permissionService.getPermissionById(id);
        return permission != null ? ResponseEntity.ok(permission) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePermission(@PathVariable Long id, @RequestBody PermissionDTO permissionDTO) {
        String responseMessage = permissionService.updatePermission(id, permissionDTO);
        return responseMessage.equals("Permission updated successfully!") 
                ? ResponseEntity.ok(responseMessage) 
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        String responseMessage = permissionService.deletePermission(id);
        return responseMessage.equals("Permission deleted successfully!")
                ? ResponseEntity.ok(responseMessage)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }
}
