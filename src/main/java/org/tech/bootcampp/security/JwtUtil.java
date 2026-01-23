package org.tech.bootcampp.security;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

 
@Component
public class JwtUtil {

	 
	   private static final String SECRET =
		        "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";

		    private final SecretKey key =
		        Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(SECRET));
	
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {

        return Jwts.parserBuilder()
//                .setSigningKey(SECRET.getBytes())
                .setSigningKey(key)

                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

