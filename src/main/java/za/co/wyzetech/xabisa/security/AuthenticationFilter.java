package za.co.wyzetech.xabisa.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.security.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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

    log.debug("token : " + accessToken);
    Claims claims = jwtUtil.resolveClaims(accessToken);

    if (claims != null & jwtUtil.validateClaims(claims)) {
      String username = claims.getSubject();
      log.info("subject : {}", username);
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, "", new ArrayList<>());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    chain.doFilter(request, response);
  }
}
