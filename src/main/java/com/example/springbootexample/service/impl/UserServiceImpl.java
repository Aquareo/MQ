package com.example.springbootexample.service.impl;

import com.example.springbootexample.service.UserService;
import com.example.springbootexample.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springbootexample.mapper.UserMapper;
import com.example.springbootexample.entity.User;
import com.example.springbootexample.dto.LoginResponse;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private static final String SECRET_KEY = "your-very-secure-and-long-secret-key-123456";

    @Autowired
    private JwtUtils jwtUtils;


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

    @Override
    public LoginResponse login(String username, String password) {
        // 1. 查询用户
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在！");
        }

        // 2. 检查密码（如果以后加密，用 passwordEncoder）
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误！");
        }

        // 3. 生成 JWT
        String token = jwtUtils.generateToken(user.getUsername()); // 用 JwtUtils 生成
        long expiresIn = 3600; // 1小时过期（秒）

        // 4. 返回响应
        return new LoginResponse(token, expiresIn, user);
    }

}