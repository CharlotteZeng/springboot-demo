package com.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.springbootdemo.entity.User;
import com.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(@RequestBody User user){
        User user1 = userService.findUser(user);
        if (null==user1) {
            new Exception("登陆失败！用户名或密码错误");
            return null;
        }
        return JSON.toJSONString(user1);

    }
}
