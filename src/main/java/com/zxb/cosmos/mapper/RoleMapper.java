package com.zxb.cosmos.mapper;

import com.zxb.cosmos.pojo.Role;
import com.zxb.cosmos.pojo.User;

import java.util.List;

public interface RoleMapper {

    List<Role> getRolesByUserID(User user);

}
