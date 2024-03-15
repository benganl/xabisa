package za.co.wyzetech.xabisa.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.security.utils.JwtUtils;

@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private final String TOKEN_HEADER = "Authorization";
  private final String TOKEN_PREFIX = "Bearer ";

  private final JwtUtils jwtUtil;

  public AuthenticationFilter(JwtUtils jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {

    final String bearerToken = request.getHeader(TOKEN_HEADER);
    if (Objects.isNull(bearerToken) || !bearerToken.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    String accessToken = bearerToken.substring(TOKEN_PREFIX.length());

    try {
      Claims claims = jwtUtil.resolveClaims(accessToken);
      if (claims != null && jwtUtil.validateClaims(claims)) {
        String username = claims.getSubject();
        Set<GrantedAuthority> roles = getRolesFromClaims(claims); // Implement logic to retrieve roles from claims
        Authentication authentication = new MyCustomAuthenticationToken(username, roles); // Custom token without password
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (JwtException e) {
      log.error("Failed to process JWT token: {}", e.getMessage());
    }

    chain.doFilter(request, response);
  }

  @SuppressWarnings("unchecked")
  private Set<GrantedAuthority> getRolesFromClaims(Claims claims) {
    if (Objects.isNull(claims)) {
      return Collections.unmodifiableSet(null);
    }

    Set<String> roles = claims.get("roles", Set.class);
    return roles.stream().map(role -> new SimpleGrantedAuthority(String.valueOf(role)))
        .collect(Collectors.toSet());

  }

  public static class MyCustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 1L;

    public MyCustomAuthenticationToken(String username, Set<GrantedAuthority> roles) {
      super(username, null, roles); // Set password to null
    }
  }
}
