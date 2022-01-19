package com.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.springbootdemo.annotation.LogAnnotation;
import com.springbootdemo.redis.RedisUtils;
import com.springbootdemo.service.HelloService;
import com.springbootdemo.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RestController
public class HelloController {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    HelloService helloService;
    /**
     * 从配置文件中寻找指定内容
     */
    @Value("${myvalue}")
    String myvalue;

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
        System.out.println("############ myvalue = "+myvalue);
        if (null==id)
            return "您没有输入id，请在url中输入?id=xxx";
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
    @RequestMapping("/index")
    public String index(){
        String s = "welcome index!";
        helloService.index(s);
        return s;
    }
}
