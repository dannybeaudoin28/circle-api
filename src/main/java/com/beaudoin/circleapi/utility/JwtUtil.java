package com.beaudoin.circleapi.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.*;

import java.util.Base64;


public class JwtUtil {
    public static String secret = "Ltpa3HAVwrdqlJfN9vduQdyBrcbeB825vm2OZKjGMro6zPXG72GRw8UGLMqAk3aF/M3LA4x6Uj5BfV4S9Li8Rw==";

    private static final long EXPIRATION_TIME = 864000000;

    public static String generateToken(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        List<String> roles = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        
        return Jwts.builder()
                .claim("email", email)
                .claim("password", password) 
                .claim("authorities", roles)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret) 
                .compact();
    }
    

    public static String extractFieldFromJWT(String token, String fieldName) {
        try {            
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            String fieldValue = claims.get(fieldName, String.class);
            
            if (fieldValue != null) {
                return fieldValue;
            } else {
                return null;
            }
        } catch (JwtException e) {
            System.err.println("Error extracting field from JWT: " + e.getMessage());
            return null;
        }
    }
    
    

    public static boolean validateToken(String token, String secret) {
        
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            boolean isValid = !claims.getBody().getExpiration().before(new Date());
            return isValid;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private static String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static boolean validateJwtToken(HttpServletRequest request) {
        String jwtToken = extractJwtToken(request);
        if (jwtToken != null && JwtUtil.validateToken(jwtToken, secret)) {
            return true;
        }
        return false;
    }

    public static List<String> extractAuthorities(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return (List<String>) claims.getBody().get("authorities");
        } catch (SignatureException e) {
            // Invalid signature or token
            return null;
        }
    }
}
