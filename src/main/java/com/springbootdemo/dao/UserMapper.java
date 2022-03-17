package com.springbootdemo.dao;

import com.springbootdemo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @SelectKey(keyProperty = "user.uid",before = true,statement = "select MD5(replace(uuid(), '-', ''))", resultType = String.class)
    @Options(useGeneratedKeys=true, keyProperty="user.uid")
    @Insert("insert into user(uname,password) VALUES(user.uname,user.password)" )
    public int insertByUser(@Param("user") User user);
    @Select("select * from user where uname = #{user.uname} and password = #{user.password}")
    public List<User> findByUser(@Param("user")User user);
    public void update(User user);
}
