package com.zxb.cosmos.pojo;

public class Permission {
    private String pid; //权限ID
    private String permissionName; //权限类型
    private String url; //资源路径
    private String permissionInfo; //权限字符串

    public Permission() {
    }

    public Permission(String pid, String permissionName, String url, String permissionInfo) {
        this.pid = pid;
        this.permissionName = permissionName;
        this.url = url;
        this.permissionInfo = permissionInfo;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(String permissionInfo) {
        this.permissionInfo = permissionInfo;
    }
}
