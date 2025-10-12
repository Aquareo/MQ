package com.example.springbootexample.dto;

import com.example.springbootexample.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;   // JWT
    private long expiresIn; // 秒
    private User user;      // 可选，返回用户信息
}