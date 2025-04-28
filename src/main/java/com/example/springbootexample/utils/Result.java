package com.example.springbootexample.utils;

import lombok.Data;

@Data

public class Result<T> {
    private int code;
    private String message;
    private T data;

    // 构造方法
    public Result() {}
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getter 和 Setter
}