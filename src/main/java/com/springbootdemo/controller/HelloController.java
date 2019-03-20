package com.springbootdemo.controller;

import com.springbootdemo.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @Autowired
    RedisUtils redisUtils;
    @RequestMapping("/hello")
    public String hello(){
        redisUtils.set("key","val");
        Object name = redisUtils.get("key");
        return "hello springboot redis:"+name;
    }
    @RequestMapping(value = {"/requestDemo"},method = {RequestMethod.GET})
    public String requestDemo(HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        return "您输入的id是:"+id;
    }
}
