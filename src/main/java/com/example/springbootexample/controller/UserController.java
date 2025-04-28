package com.example.springbootexample.controller;

import org.springframework.web.bind.annotation.*;

import com.example.springbootexample.service.*;
import com.example.springbootexample.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public void register(@RequestBody User user) 
    {
        userService.register(user);  
    }

}