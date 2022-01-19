package com.springbootdemo.service.impl;

import com.springbootdemo.annotation.LogAnnotation;
import com.springbootdemo.dao.ArticleMapper;
import com.springbootdemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    public ArticleMapper articleMapper;
    @LogAnnotation(operateType = "helloService 日志")
    @Override
    public void index(String s) {


        System.out.println("service 接受到的参数:"+s);

    }
}
