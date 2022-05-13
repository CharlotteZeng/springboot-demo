package com.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.springbootdemo.entity.User;
import com.springbootdemo.exception.BusinessException;
import com.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(@RequestBody User user,HttpServletResponse response) throws Exception{
        String userJSONString = null;
        try {
            User user1 = userService.findUser(user);
            if (null==user1) {
                throw new BusinessException("登陆失败！用户名或密码错误");
            }
            userJSONString = JSON.toJSONString(user1);
            // 对json进行编码 不然会报错java.lang.IllegalArgumentException: An invalid character [34] was present in the Cookie value
            String encode = URLEncoder.encode(userJSONString, "utf-8");
            Cookie cookie =new Cookie("user",encode);
            //设置cookie
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException();
        }
        return userJSONString;

    }
}
