package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final String SECRET = "test-secret";

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setClaims(Map.of(
                        "userId", userId,
                        "email", email,
                        "role", role
                ))
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).get("email", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }
}
