package com.zxb.cosmos.controller;

import com.zxb.cosmos.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    // 这里如果不写method参数的话，默认支持所有请求，如果想缩小请求范围，还是要添加method来支持get, post等等某个请求。
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {

        System.out.println("HomeController.login");

        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        logger.info(request.getAttribute("shiroLoginFailure").toString());
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.isInstance(exception)) {
                System.out.println("账户不存在");
                msg = "账户不存在或密码不正确";
            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                System.out.println("密码不正确");
                msg = "账户不存在或密码不正确";
            } else {
                logger.error(exception+"");
                System.out.println("其他异常");
                msg = "其他异常";
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "login";
    }

    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public String doRegister(@RequestParam("username") String username,@RequestParam("password") String password){
        boolean result =userServiceImpl.registerData(username,password);
        if(result){
            return "/login";
        }
        return "/register";
    }
    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
    @RequestMapping(value = "/register")
    public String register() {
        logger.info("register() 方法被调用");
        return "register.html";
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        logger.info("hello() 方法被调用");
        return "helloPage.html";
    }

}
