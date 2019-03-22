package com.zxb.cosmos.mapper;

import com.zxb.cosmos.pojo.Permission;
import com.zxb.cosmos.pojo.Role;
import com.zxb.cosmos.pojo.User;

import java.util.List;

public interface PermissionMapper {
    List<Permission> getPermisByRoleID(Role role);
}
