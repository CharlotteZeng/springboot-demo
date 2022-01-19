package com.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @SpringBootApplication：申明让spring boot自动给程序进行必要的配置，这个配置等同于：
 * @Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。
 */
@SpringBootApplication
/**
 * springboot启动类
 * springboot 只能扫描此类下面的路径吓得controllere
 * 所以如果controller不在application类的下面 可以加上
 * @ComponentScan(basePackages = {"com.springbootdemo.*"}) 来指定要扫描的包
 */
@ComponentScan(basePackages = {"com.springbootdemo.*"})
@EnableTransactionManagement
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
