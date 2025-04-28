package com.example.springbootexample.service.impl;

import com.example.springbootexample.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springbootexample.mapper.UserMapper;
import com.example.springbootexample.entity.User;
import com.example.springbootexample.dto.LoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private static final String SECRET_KEY = "your-very-secure-and-long-secret-key-123456";


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
        // 查询用户
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在！");
        }

        // 检查密码
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误！");
        }

        
        /* 
        // 检查密码（加密匹配）
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密码错误！");
        }
        后续需要做到：数据库中存储的是经过加密（例如 bcrypt）后的密码摘要（hash）。
        用户登录时：
        1.服务器不会直接比较用户输入的密码和数据库中的密码。
        2.而是将用户输入的密码进行相同的加密操作，然后与数据库中的密码摘要进行比较。
        */

        // 将字符串密钥转换为 SecretKey
        SecretKey secretKey = new SecretKeySpec(
            SECRET_KEY.getBytes(StandardCharsets.UTF_8),
            SignatureAlgorithm.HS256.getJcaName()
        );

        // 生成 JWT Token
        String token = Jwts.builder()
            .setSubject(user.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 小时
            .signWith(secretKey, SignatureAlgorithm.HS256) // 使用 SecretKey
            .compact();
        // 这里可以设置 token 的过期时间，例如 1 小时


        // 返回登录成功的响应
        return new LoginResponse(token,3600,user);
    }
}