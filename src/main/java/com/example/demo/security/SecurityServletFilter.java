package com.example.demo.security;

import com.example.demo.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class SecurityServletFilter extends HttpFilter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        String token = request.getHeader("Authorization");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Request-Method", "GET, PUT");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        if (validSite(request.getRequestURI()) || authenticated(token) || request.getMethod().equals("OPTIONS")) {
            chain.doFilter(request, response); // (4)
            return;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
    }

    private boolean validSite(String sitePath) {
        if (sitePath.equals("/user/login")) return true;
        if (sitePath.equals("/user/register")) return true;
        if (sitePath.startsWith("/image/")) return true;
        if (sitePath.startsWith("/test")) return true;
        return false;
    }

    private boolean authenticated(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}