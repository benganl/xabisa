package za.co.wyzetech.xabisa.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.security.exception.AuthException;
import za.co.wyzetech.xabisa.security.utils.JwtUtils;

@Slf4j
@Service
class DefaultSecurityService implements SecurityService {

  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authManager;
  private final SecurityRepository securityRepository;
  private final JwtUtils jwtUtil;
  private final Long expiration;

  public DefaultSecurityService(PasswordEncoder passwordEncoder,
      SecurityRepository securityRepository, JwtUtils jwtUtil,
      @Value("${wyzecms.security.token.expiration:604800}") Long expiration,
      AuthenticationManager authManager) {
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.expiration = expiration;
    this.securityRepository = securityRepository;
    this.authManager = authManager;
  }

  @Override
  public String generateToken(String username) {
    return jwtUtil.generateToken(username, expiration);
  }

  @Override
  public Boolean validateToken(String token, String username) {
    try {
      return jwtUtil.validateToken(token, username);
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public Boolean isTokenExpired(String token) {
    return jwtUtil.isTokenExpired(token);
  }

  @Override
  public String getUsernameFromToken(String token) {
    return jwtUtil.getUsernameFromToken(token);
  }

  @Override
  public Authentication authenticate(String username, String password) {
    final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
    try {
      return authManager.authenticate(authToken);
    } catch (Exception d) {
      log.warn("Authentication failed for user: {}. Problem: {}", username, d.getMessage());
      throw new AuthException("Authentication error!!!", d);
    }
  }
}
