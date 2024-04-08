package com.beaudoin.circleapi.utility;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import java.util.Date;

import io.jsonwebtoken.*;
public class JwtUtil {

    // Generate a secure signing key for HS512 algorithm
    private static final SecretKey secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

    // Print the Base64 encoded representation of the key
    private static final String SECRET = java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded());

    private static final long EXPIRATION_TIME = 864000000;

    public static String generateToken(String email, String password) {
        return Jwts.builder()
                .claim("email", email) // Add email as a claim
                .claim("password", password) // Add password as a claim
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
