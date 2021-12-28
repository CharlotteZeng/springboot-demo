package com.springbootdemo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public void index(String s) {
        System.out.println("service 接受到的参数:"+s);
    }
}
