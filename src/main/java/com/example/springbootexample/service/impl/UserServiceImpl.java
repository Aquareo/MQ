package com.example.springbootexample.service.impl;

import com.example.springbootexample.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserById(Long id) {
        return "User with ID: " + id;
    }
}