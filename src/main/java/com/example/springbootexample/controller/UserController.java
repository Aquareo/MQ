package com.example.springbootexample.controller;

import org.springframework.web.bind.annotation.*;

import com.example.springbootexample.service.*;
import com.example.springbootexample.entity.User;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.springbootexample.dto.LoginRequest;
import com.example.springbootexample.dto.LoginResponse;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody User user) 
    {
        try{
            userService.register(user); 
        }
        catch (Exception e) {
            System.out.println("注册失败: " + e.getMessage());
        }
    }

    /* 
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        } catch (Exception e) {
            System.out.println("登录失败: " + e.getMessage());
            return null;
        }

    }
    */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("进入登录接口，用户名：" + loginRequest.getUsername());  // 加日志
        try {
            LoginResponse response = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(response);// 返回 token + 用户信息
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
        }
    }


}