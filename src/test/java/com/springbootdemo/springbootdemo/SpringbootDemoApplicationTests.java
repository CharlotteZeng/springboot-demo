package com.springbootdemo.springbootdemo;

import com.springbootdemo.dao.UserMapper;
import com.springbootdemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@ComponentScan(basePackages = {"com.springbootdemo.*"})
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
        User u = new User();
        u.setPassword("1234");
        u.setUsername("firstUser");
        userMapper.insert(u);
        System.out.println("end");
    }

}
