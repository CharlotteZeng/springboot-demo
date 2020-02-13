package com.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.springbootdemo.annotation.LogAnnotation;
import com.springbootdemo.redis.RedisUtils;
import com.springbootdemo.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @Autowired
    RedisUtils redisUtils;
    @LogAnnotation(operateType = "日志注解")
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
    //跨域注解
    @CrossOrigin
    @RequestMapping(value = {"/requestAES"},method = {RequestMethod.GET})
    public String requestAES(HttpServletRequest request, HttpServletResponse response) {
        String encrypt = "错误";
        JSONObject jo = new JSONObject();
        try {
            response.setHeader("Access-Control-Allow-Origin","*");
            String json = request.getParameter("json");
            String key = "DmhzsbcNSzaMDRz5EJcZPQ==";
            String decrypt = AESUtil.decrypt(json, key);
            System.out.println("decrypt:"+decrypt);
            encrypt = AESUtil.encrypt("18611552609", key);
            System.out.println("encrypt:"+encrypt);
            jo.put("encrypt",encrypt);
        } catch (Exception e) {
            System.out.println("error!");
            e.printStackTrace();
        }
        return jo.toString();
    }
}
