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
    public void register(User user) 
    {
        userMapper.insertUser(user);
    }
}