package com.zxb.cosmos.service.impl;


import com.zxb.cosmos.mapper.PermissionMapper;
import com.zxb.cosmos.pojo.Permission;
import com.zxb.cosmos.pojo.Role;
import com.zxb.cosmos.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据用户Id查询用户的所有权限
     * @param
     * @return
     */
    @Override
    public List<Permission> getPermisByRoleID(List<Role> roles) {
        List<Permission> permissions = new ArrayList<Permission>();
        for(int i=0;i< roles.size();i++){
            permissions.addAll(permissionMapper.getPermisByRoleID(roles.get(i)));
        }
        return permissions;
    }
}
