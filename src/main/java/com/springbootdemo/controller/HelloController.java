package com.springbootdemo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello springboot";
    }
    @RequestMapping(value = {"/requestDemo"},method = {RequestMethod.GET})
    public String requestDemo(HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        return "您输入的id是:"+id;
    }
}
