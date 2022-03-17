package com.springbootdemo.service;

import com.springbootdemo.entity.User;

public interface UserService {

    public User findUser(User user);
    public void register(User user);
}
