package com.SpringProject.userManagement.util;

//public class JwtUtil {
    //package com.SpringProject.userManagement.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

    @Component
    public class JwtUtil {

        @Value("${jwt.secret}")
        private String SECRET_KEY;

        @Value("${jwt.expiration}")
        private long EXPIRATION_TIME;

        // Generate Secret Key
        private Key getSignKey() {
            return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        }

        // Generate JWT Token
        public String generateToken(String email) {

            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(getSignKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        // Extract Username (Email)
        public String extractUsername(String token) {
            return extractAllClaims(token).getSubject();
        }

        // Extract All Claims
        public Claims extractAllClaims(String token) {

            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        // Validate Token
        public boolean validateToken(String token, String email) {

            String username = extractUsername(token);

            return username.equals(email)
                    && !extractAllClaims(token).getExpiration().before(new Date());
        }
    }

