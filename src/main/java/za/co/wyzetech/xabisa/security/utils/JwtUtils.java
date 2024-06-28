package za.co.wyzetech.xabisa.security.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

  private final SecretKey key;
  private final JwtParser jwtParser;
  private final long expirationInSeconds;

  public JwtUtils(
      @Value("${xabisa.security.secret:S3crEtn0t54f33hBf&g9jHg4Y6bgDre36vcX7}") String secret,
      @Value("${xabisa.security.expiration:604800}") long expiration) {
    this.expirationInSeconds = expiration;
    key = Keys.hmacShaKeyFor(secret.getBytes());
    jwtParser = Jwts.parser().verifyWith(key).build();
  }
  
  public String generateToken(String username, Set<String> roles) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expiryLocalDate = now.plus(expirationInSeconds, ChronoUnit.SECONDS);
    
    Date dateNow = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    Date dateExpiry = Date.from(expiryLocalDate.atZone(ZoneId.systemDefault()).toInstant());

    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", roles);
    claims.put("username", username);
    claims.put("issuedAt", dateNow);

    log.info("Key: {}", key.getEncoded());

    return Jwts.builder()
        .subject(username)
        .signWith(key)
        .claims(claims)
        .expiration(dateExpiry)
        .compact();
  }

  public Boolean validateToken(String token, String username) {
    try {
      return jwtParser.parseSignedClaims(token).getPayload().getSubject().equals(username);
    } catch (Exception e) {
      return false;
    }
  }

  public String getUsernameFromToken(String token) {
    return jwtParser.parseSignedClaims(token).getPayload().getSubject();
  }

  public Boolean isTokenExpired(String token) {
    Claims payload = jwtParser.parseSignedClaims(token).getPayload();
    Date expiration = payload.getExpiration();
    return expiration.before(new Date());
  }

  public Claims resolveClaims(String token) {
    return jwtParser.parseSignedClaims(token).getPayload();
  }

  public Boolean validateClaims(Claims claims) {
    try {
      return claims.getExpiration().after(new Date());
    } catch (Exception e) {
      throw e;
    }
  }
}
