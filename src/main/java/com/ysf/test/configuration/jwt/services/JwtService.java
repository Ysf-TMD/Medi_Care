package com.ysf.test.configuration.jwt.services;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey ;

    @Value("${jwt.expiration}")
    private long expirationMs ;


    public String genererToken(UserDetails user , String role ) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ expirationMs))
                .signWith(getSigningKey())
                .compact();
    }


    public boolean estValid(String token , UserDetails user) {
        String username = extraireUsername(token) ;
        return username.equals(user.getUsername()) && !estExpire(token) ;
    }
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String extraireUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private boolean estExpire(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }
}
