package com.zxb.cosmos.service;


import com.alibaba.fastjson.JSONObject;
import com.zxb.cosmos.pojo.User;

import java.util.List;

public interface UserService {

    int insUser(User user);
    int updateUser(User user);
    int deleteUser(User user);
    /**查询用户列表*/
    List<User> selectUsers(JSONObject jsonObject);
    Integer getCountNum(User user);

    /**
     * 根据用户信息 如uid username 查询用户的详细信息
     * @param user
     * @return
     */
    User getUserInfo(User user);


    boolean registerData(String userName,String password);
}
