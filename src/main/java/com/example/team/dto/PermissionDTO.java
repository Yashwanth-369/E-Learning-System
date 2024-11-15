package com.example.team.dto;

public class PermissionDTO {
    private Long permissionId;
    private Long roleId;
    private String permissionTitle;
    private String permissionModule;
    private String permissionDescription;

    // Constructors, Getters, Setters
    public PermissionDTO() {}

    public PermissionDTO(Long permissionId, Long roleId, String permissionTitle, String permissionModule, String permissionDescription) {
        this.permissionId = permissionId;
        this.roleId = roleId;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
