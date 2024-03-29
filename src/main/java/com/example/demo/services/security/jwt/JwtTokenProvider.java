package com.example.demo.services.security.jwt;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtTokenProvider {
    @Value("${login.secret}")
    String loginSecret;

    @Value("${login.token-expire-time}")
    String expireTime;

    // Tạo ra jwt từ thông tin user
    public String generateToken(ResponseUserDTO userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireTime);
        System.out.println("Expired in" + expiryDate);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(userDetails.getId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, loginSecret)
                .compact();
    }

    public String generateToken(Map<String, Object> header, String payload, String secret, SignatureAlgorithm algorithm) {
        return Jwts.builder()
                .setHeader(header)
                .setPayload(payload)
                .signWith(algorithm, secret)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(loginSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public String validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(loginSecret).parseClaimsJws(authToken);
            return getUserIdFromJWT(authToken);
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return null;
    }
}
