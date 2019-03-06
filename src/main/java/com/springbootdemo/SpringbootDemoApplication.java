package com.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * springboot启动类
 * springboot 只能扫描此类下面的路径吓得controllere
 * 所以如果controller不在application类的下面 可以加上
 * @ComponentScan(basePackages = {"com.springbootdemo.*"}) 来指定要扫描的包
 */
//@ComponentScan(basePackages = {"com.springbootdemo.*"})
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
