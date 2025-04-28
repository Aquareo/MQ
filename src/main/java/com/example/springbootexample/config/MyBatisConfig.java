package com.example.springbootexample.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.springbootexample.mapper")
public class MyBatisConfig {
}