package za.co.wyzetech.xabisa.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.security.exception.AuthException;
import za.co.wyzetech.xabisa.security.utils.JwtUtils;

@Slf4j
@Service
class DefaultSecurityService implements SecurityService {

  private final PasswordEncoder passwordEncoder;
  // private final AuthenticationManager authManager;
  private final SecurityRepository securityRepository;
  private final JwtUtils jwtUtil;
  private final Long expiration;

  public DefaultSecurityService(PasswordEncoder passwordEncoder,
      SecurityRepository securityRepository, JwtUtils jwtUtil,
      @Value("${wyzecms.security.token.expiration:604800}") Long expiration) {
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.expiration = expiration;
    this.securityRepository = securityRepository;
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
  public String getUsernameFromToken(String token) {
    return jwtUtil.getUsernameFromToken(token);
  }

  @Override
  public Boolean isTokenExpired(String token) {
    return jwtUtil.isTokenExpired(token);
  }

  @Override
  public Authentication authenticate(String username, String password) {
    final UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(username, password);
    try {
      // return authManager.authenticate(authToken);
      return null;
    } catch (Exception d) {
      log.warn("Authentication failed for user: {}. Problem: {}", username, d.getMessage());
      throw new AuthException("Authentication error!!!", d);
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);
    if (Objects.nonNull(user)) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
          user.getPassword(), getAuthorities(user.getRoles()));
    } else {
      throw new UsernameNotFoundException("Incorrect and/or invalid credentials");
    }
  }

  private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toSet());
  }

  public User findByUsername(String username) {
    return securityRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User not found with username: " + username));
  }

  @Override
  public void createUser(String username, String password) {
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setPassword(password);
    newUser.setRoles(null);
    securityRepository.save(newUser);
  }
}
