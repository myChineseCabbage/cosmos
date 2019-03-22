package com.zxb.cosmos.service;

import com.zxb.cosmos.pojo.Permission;
import com.zxb.cosmos.pojo.Role;

import java.util.List;

public interface PermissionService {

    /**
     *根据用户id查询用户的所有权限
     * @param  roles
     * @return
     */
    List<Permission> getPermisByRoleID(List<Role> roles);
}
