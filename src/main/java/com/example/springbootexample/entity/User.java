package com.example.springbootexample.entity;

import lombok.Getter;
import lombok.Setter;

@Getter // 自动生成 Getter 方法
@Setter // 自动生成 Setter 方法
public class User {
    private int id; // 主键
    private String username;
    private String password;

    // 无参构造函数（Lombok 默认会生成）
}