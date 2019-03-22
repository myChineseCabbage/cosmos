package com.zxb.cosmos.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.zxb.cosmos.mapper.UserMapper;
import com.zxb.cosmos.pojo.User;
import com.zxb.cosmos.service.UserService;
import com.zxb.cosmos.utils.PageBean;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

    /**
     * 根据用户的信息查询用户的详细信息
     * @param user
     * @return
     */
    @Override
    public User getUserInfo(User user) {

        return userMapper.getUserInfoByUid(user);
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean registerData(String userName, String password) {
        //生成uuid
        String uid = "123123";
        //将用户名作为盐值
        ByteSource salt  = ByteSource.Util.bytes(userName);
        /**
         * md5加密
         * 使用SimpleHash类对原始密码进行加密
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值 ，即用户名
         * 第四个参数为加密次数
         * 最后toHex() 方法将加密后的密码转换成String
         *
         */
        String newPasswordmd5 = new SimpleHash("MD5",password,salt,2).toHex();
        User user = new User();
        user.setUid(uid);
        user.setUserName(userName);
        user.setPassword(newPasswordmd5);
        user.setSalt(salt.toHex());
        user.setUserNick(userName+"nick");
        user.setCreateTime("2019.3.22");
        user.setState("1");

        List<User> userList = userMapper.selectUsers(user);
        logger.info("userList"+userList);
        if(userList.size()<1){
            logger.info("新增用户");
            userMapper.insUser(user);
            return  true;
        }
        return false;
    }
}
