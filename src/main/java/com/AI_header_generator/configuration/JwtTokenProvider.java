package com.AI_header_generator.configuration;

import com.AI_header_generator.models.UserAdapter;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        UserAdapter userPrincipal = (UserAdapter) authentication.getPrincipal();

        Instant now = Instant.now();
        Instant expiryDate = now.plus(Duration.ofDays(1));

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("email", userPrincipal.getEmail())
                .claim("role", userPrincipal.getAuthorities().stream().findFirst().orElseThrow(() -> new RuntimeException("No authority found")).getAuthority())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiryDate))
                .signWith(key)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
            return true;
        } catch (SecurityException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token" + authToken);
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }
    public SecretKey getKey() {
        return key;
    }
}