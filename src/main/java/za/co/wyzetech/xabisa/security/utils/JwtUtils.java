package za.co.wyzetech.xabisa.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

    private final SecretKey key;

    public JwtUtils(@Value("${wyzecms.security.secret}") String secret,
            @Value("${wyzecms.security.token.expiration:604800}") Long expiration) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username, Long expiration) {
        Date expiryDate = new Date(new Date().getTime() + expiration);
        return generateToken(username, expiryDate);
    }

    public String generateToken(String username, Date expiryDate) {
        Map<String, ?> claims = new HashMap<>();
        log.info("Key: {}", key.getEncoded());
        return Jwts.builder().subject(username).signWith(key).claims(claims).expiration(expiryDate).compact();
    }

    public Boolean validateToken(UserDetails userDetails, SecretKey key, String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject().equals(userDetails
                    .getUsername());
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validateToken(String token, String username) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject().equals(username);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public Boolean isTokenExpired(String token) {
        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        Date expiration = payload.getExpiration();
        return expiration.before(new Date());
    }

    public Claims resolveClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public Boolean validateClaims(Claims claims) {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }
}
