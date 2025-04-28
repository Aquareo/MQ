package com.example.springbootexample.mapper;

import com.example.springbootexample.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(int id);

    // 通过用户名查询用户
    @Select("SELECT * FROM user WHERE username = #{username}")
    User getUserByUsername(String username);


    //注册用户
    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 主键回填，数据库自动生成的主键（比如 id）会自动回填到你的 user 对象里。
    void insertUser(User user);


    
}