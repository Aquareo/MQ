package com.example.springbootexample.service;
import com.example.springbootexample.entity.User;
import com.example.springbootexample.dto.LoginResponse;

public interface UserService {
    
    void register(User user);

    LoginResponse login(String username, String password);
    
}