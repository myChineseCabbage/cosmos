package com.zxb.cosmos.mapper;

import com.zxb.cosmos.pojo.User;

import java.util.List;

public interface UserMapper {

    int insUser(User user);
    int updateUser(User user);
    int deleteUser(User user);

    List<User> selectUsers(User user);
    Integer getCountNum(User user);

}
