package com.example.springbootexample.service.impl;

import com.example.springbootexample.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springbootexample.mapper.UserMapper;
import com.example.springbootexample.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在！");
        }

        // 插入新用户
        userMapper.insertUser(user);
    }
}