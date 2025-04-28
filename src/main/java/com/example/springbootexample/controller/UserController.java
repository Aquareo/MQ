package com.example.springbootexample.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return "User with ID: " + id;
    }
}