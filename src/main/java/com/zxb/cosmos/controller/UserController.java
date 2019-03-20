package com.zxb.cosmos.controller;

import com.alibaba.fastjson.JSONObject;
import com.zxb.cosmos.pojo.User;
import com.zxb.cosmos.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userServiceImpl;

    @ApiOperation(value = "新增用户列表",notes = "")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
        try {
            int i = userServiceImpl.insUser(user);
            if(i>=1){
                return "新增成功";
            }
            return  "新增失败";
        }catch (Exception e){
            return "修改失败";
        }

    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        try {
            int i = userServiceImpl.updateUser(user);
            if(i>=1){
                return "修改成功";
            }

            return "修改失败";
        } catch (Exception e){

            return "修改失败";
        }
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @RequestMapping("deleteUser")
    public String deleteUser(@RequestBody User user){
        try{
            //此处删除的同时牵涉到要删除其关联的数据表 同时删除 后期完善
            int i = userServiceImpl.deleteUser(user);
            if(i>=1){
                return "删除成功";
            }
            return "删除失败";
        }catch (Exception e){
            return "删除失败";
        }
    }

    /**
     * 查询用户列表
     * @param
     * @return
     */
    @RequestMapping("/selUsers")
    public List<User> selUsers(@RequestBody JSONObject jsonObj){
        logger.info("查询用户列表");
        return userServiceImpl.selectUsers(jsonObj);
    }
}
