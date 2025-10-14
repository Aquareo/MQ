package com.example.springbootexample.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys; // 新增这个导入
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印调试信息
        System.out.println("请求路径：" + request.getRequestURI());
        System.out.println("收到的 Authorization 头：" + request.getHeader("Authorization"));

        // 放行逻辑不变
        String path = request.getRequestURI();
        if (path.startsWith("/user/login") || path.startsWith("/user/register") ||
                path.startsWith("/static") || path.startsWith("/swagger") || "GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\":401,\"message\":\"Missing or invalid Authorization header\"}");
            return false;
        }

        String token = auth.substring(7);
        try {
            // 关键修改：用和生成时完全相同的方式处理密钥
            Claims claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(JWT_SECRET.getBytes())) // 这里改了
                    .parseClaimsJws(token)
                    .getBody();
            request.setAttribute("userId", claims.get("userId"));
            return true;
        } catch (Exception ex) {
            // 打印具体错误原因（方便排查）
            ex.printStackTrace(); // 新增这行，查看控制台的详细错误
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\":401,\"message\":\"Invalid token: " + ex.getMessage() + "\"}");
            return false;
        }
    }
}