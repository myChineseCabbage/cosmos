package com.zxb.cosmos.service;

import com.zxb.cosmos.pojo.Role;
import com.zxb.cosmos.pojo.User;

import java.util.List;

public interface RoleService {

    /**
     * 根据用户ID查询用户的所有角色
     * @param
     * @return
     */
    List<Role> getRolesByUid(String userId);
}
