package com.springbootdemo.service.impl;

import com.springbootdemo.annotation.LogAnnotation;
import com.springbootdemo.dao.UserMapper;
import com.springbootdemo.entity.User;
import com.springbootdemo.service.UserService;
import com.springbootdemo.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Value("${user.security.aes.key}")
    private String aseKey;
    @Autowired
    private UserMapper userMapper;
    @LogAnnotation(operateType = "userervice 日志")
    @Override
    public User findUser(User user) {
        try {
            String decryptPassword = AESUtil.decrypt(user.getPassword(), aseKey);
            user.setPassword(decryptPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> byUser = userMapper.findByUser(user);
        if (null==byUser||byUser.size()<=0){
            return null;
        }

        return byUser.get(0);
    }
    @Transactional
    @Override
    public void register(User user) {
        try {
            String encryptPassword = AESUtil.encrypt(user.getPassword(), aseKey);
            user.setPassword(encryptPassword);
            userMapper.insertByUser(user);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
