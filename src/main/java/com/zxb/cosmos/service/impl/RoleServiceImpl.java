package com.zxb.cosmos.service.impl;

import com.zxb.cosmos.mapper.RoleMapper;
import com.zxb.cosmos.pojo.Role;
import com.zxb.cosmos.pojo.User;
import com.zxb.cosmos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户ID查询用户的所有角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> getRolesByUid(String userId) {
        User user = new User();
        user.setUid(userId);
        return roleMapper.getRolesByUserID(user);
    }
}
