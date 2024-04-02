package com.example.demo.security;

import com.example.demo.services.security.jwt.JwtTokenProvider;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.services.tables.UserServiceJPA;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityServletFilter extends HttpFilter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserServiceJPA userServiceJPA;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        String token = request.getHeader("Authorization");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Request-Method", "GET, PUT");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        if (validSite(request.getRequestURI()) || authenticated(token) != null || request.getMethod().equals("OPTIONS")) {
            String userId = authenticated(token);
            UserEntity userEntity = userServiceJPA.findByUserId(userId);
            request.setAttribute("userInfo", userEntity);
            chain.doFilter(request, response); // (4)
            return;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
    }

    private boolean validSite(String sitePath) {
        if (sitePath.startsWith("/user/login")) return true;
        if (sitePath.equals("/user/register")) return true;
        if (sitePath.equals("/user/admin-account/create")) return true;
        if (sitePath.startsWith("/test")) return true;
        if (sitePath.startsWith("/payment")) return true;
        if (sitePath.startsWith("/game")) return true;
        if (sitePath.startsWith("/card")) return true;
        if (sitePath.startsWith("/item")) return true;
        return false;
    }

    private String authenticated(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}