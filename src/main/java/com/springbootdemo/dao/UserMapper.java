package com.springbootdemo.dao;

import com.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public void insert(User user);
    public void update(User user);
}
