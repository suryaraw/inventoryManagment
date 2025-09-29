package com.inventory.rootPackage.jwt;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
	
    private final  SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // keep safe!
    
    private final long jwtExpiration = 60 * 60; // 1 hour in seconds
    
    public String generateToken(String username, String role) {
    	System.out.println(username +role +secretKey);
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000)) // 1 hour
                .signWith(secretKey)
                .compact();
    }
    
    public long getJwtExpiration() {
        return jwtExpiration;
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().get("role", String.class);
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}

