package com.example.team.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    
    @ManyToOne
    @JoinColumn(name = "permission_role_id")
    private Role role;
    
    private String permissionTitle;
    private String permissionModule;
    private String permissionDescription;

    // Constructors, Getters, Setters
    public Permission() {}

    public Permission(Role role, String permissionTitle, String permissionModule, String permissionDescription) {
        this.role = role;
        this.permissionTitle = permissionTitle;
        this.permissionModule = permissionModule;
        this.permissionDescription = permissionDescription;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPermissionTitle() {
        return permissionTitle;
    }

    public void setPermissionTitle(String permissionTitle) {
        this.permissionTitle = permissionTitle;
    }

    public String getPermissionModule() {
        return permissionModule;
    }

    public void setPermissionModule(String permissionModule) {
        this.permissionModule = permissionModule;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
}
