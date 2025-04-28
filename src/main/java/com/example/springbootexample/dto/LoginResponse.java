package com.example.springbootexample.dto;

import lombok.Data;
import com.example.springbootexample.entity.User;


@Data
public class LoginResponse {
    private String token;
    private int expiresIn;
    private User user;

    public LoginResponse(String token, int expiresIn, User user) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.user = user;
    }

    // Getters and Setters
}
