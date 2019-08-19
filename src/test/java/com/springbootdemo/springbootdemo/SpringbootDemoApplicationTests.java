package com.springbootdemo.springbootdemo;

import com.springbootdemo.dao.UserMapper;
import com.springbootdemo.springbootdemo.bean.SpringScopeTestBean;
import com.springbootdemo.entity.User;
import com.springbootdemo.utils.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@ComponentScan(basePackages = {"com.springbootdemo.*"})
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SpringScopeTestBean springScopeTestBean;
    @Test
    public void contextLoads() {
        User u = new User();
        u.setPassword("1234");
        u.setUsername("firstUser");
        userMapper.insert(u);
        System.out.println("end");
    }
    @Test
    public void testScopeBean() {
        springScopeTestBean.setVal("zhc");
        System.out.println("====================================");
        System.out.println("cpu核心数："+Runtime.getRuntime().availableProcessors());
        System.out.println("springScopeTestBean.getVal():"+springScopeTestBean.getVal());
        SpringScopeTestBean springScopeTestBean1 = SpringUtil.getBean("springScopeTestBean", SpringScopeTestBean.class);
        SpringScopeTestBean springScopeTestBean2 = SpringUtil.getBean("springScopeTestBean", SpringScopeTestBean.class);
        System.out.println("springScopeTestBean1.getVal():"+springScopeTestBean1.getVal());
        System.out.println("springScopeTestBean2.getVal():"+springScopeTestBean2.getVal());
        System.out.println("是否单例："+springScopeTestBean1.equals(springScopeTestBean2));
        System.out.println("springScopeTestBean1.hashCode()："+springScopeTestBean1);
        System.out.println("springScopeTestBean2.hashCode()："+springScopeTestBean2);
        System.out.println("====================================");


    }

}
