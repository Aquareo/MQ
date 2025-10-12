package com.example.springbootexample.config;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtInterceptor implements HandlerInterceptor {

    // 注意：把 secret 保存在配置文件或 env 中
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 打印请求路径和收到的 Authorization 头
        System.out.println("请求路径：" + request.getRequestURI());
        System.out.println("收到的 Authorization 头：" + request.getHeader("Authorization"));


        String path = request.getRequestURI();
        // 放行登录注册和静态资源
        if (path.startsWith("/user/login") || path.startsWith("/user/register") ||
                path.startsWith("/static") || path.startsWith("/swagger") || "GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"Missing or invalid Authorization header\"}");
            return false;
        }
        String token = auth.substring(7);
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            // 你可以把 userId 放入 request attribute 供 Controller 读取
            request.setAttribute("userId", claims.get("userId"));
            return true;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"Invalid token\"}");
            return false;
        }
    }
}
