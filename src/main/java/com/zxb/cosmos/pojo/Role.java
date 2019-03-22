package com.zxb.cosmos.pojo;

public class Role {
    private String roleId; //角色ID
    private String roleName; //角色名字
    private String description; // 角色描述
    private Boolean available = Boolean.FALSE; //是否可用，如果不可用将不会添加给用户

    public Role() {
    }

    public Role(String roleId, String roleName, String description, Boolean available) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
        this.available = available;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
