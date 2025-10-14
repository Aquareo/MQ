package com.example.springbootexample.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys; // 新增这个导入
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String SECRET;

    private static final long EXPIRATION = 1000 * 60 * 60 * 2; // 2小时

    public String generateToken(String username) {

        // 新增日志：打印生成 token 时用的密钥（注意：正式环境要删掉，避免泄露）
        //System.out.println("生成 token 用的密钥：" + SECRET);

        // 关键修改：用 Keys.hmacShaKeyFor 处理密钥
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256) // 这里改了
                .compact();
    }

    public String getUsernameFromToken(String token) {
        // 验证时也要用相同方式处理密钥
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes())) // 这里改了
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes())) // 这里改了
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}