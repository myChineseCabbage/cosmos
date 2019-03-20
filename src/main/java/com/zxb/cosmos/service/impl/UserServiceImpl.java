package com.zxb.cosmos.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.zxb.cosmos.mapper.UserMapper;
import com.zxb.cosmos.pojo.User;
import com.zxb.cosmos.service.UserService;
import com.zxb.cosmos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 单条新增用户
     * @param user
     * @return
     */
    @Override
    public int insUser(User user) {
        return userMapper.insUser(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    /**
     * 查询用户列表
     * @param
     * @return
     */
    @Override
    public List<User> selectUsers(JSONObject jsonObject) {
        User user = new User();
        JSONObject userObj = jsonObject.getJSONObject("user");
        user.setUid(userObj.getString("uid"));
        user.setUserName(userObj.getString("userName"));
        int currentPage = jsonObject.getInteger("currentPage");
        int pageSize = jsonObject.getInteger("pageSize");

        PageHelper.startPage(currentPage,pageSize);//一定要在在调用Mapper方法之前设置好这些值
        List<User> userList = userMapper.selectUsers(user);
        Integer countNums = userMapper.getCountNum(user);
        PageBean<User> pageData = new PageBean<User>(currentPage,pageSize,countNums);
        pageData.setItems(userList);
        return pageData.getItems();
    }

    @Override
    public Integer getCountNum(User user) {
        return null;
    }
}
