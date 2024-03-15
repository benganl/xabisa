package za.co.wyzetech.xabisa.security;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.security.exception.SecurityException;
import za.co.wyzetech.xabisa.security.models.LoginResult;
import za.co.wyzetech.xabisa.security.models.Registration;
import za.co.wyzetech.xabisa.security.utils.JwtUtils;

@Service
public interface SecurityService {

  LoginResult authenticate(String username, String password, Set<String> roles);
  
  String generateToken(String username, Set<String> roles);

  Boolean validateToken(String token, String username);

  String getUsernameFromToken(String token);

  Boolean isTokenExpired(String token);

  void createUser(Registration registration) throws SecurityException;


}


@Slf4j
@Service
class DefaultSecurityService implements SecurityService {

  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authManager;
  private final SecurityRepository securityRepository;
  private final JwtUtils jwtUtil;
  private final Long expiration;
  private final AuthService authService;

  public DefaultSecurityService(PasswordEncoder passwordEncoder,
      SecurityRepository securityRepository, JwtUtils jwtUtil,
      @Value("${wyzecms.security.token.expiration:604800}") Long expiration,
      AuthenticationManager authManager, AuthService authService) {
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.expiration = expiration;
    this.securityRepository = securityRepository;
    this.authManager = authManager;
    this.authService = authService;
  }

  @Override
  public String generateToken(String username, Set<String> roles) {
    return jwtUtil.generateToken(username, roles);
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
  public void createUser(Registration registration) throws SecurityException {
    String username = registration.getEmail();
    String password = registration.getPassword();

    Optional<SysUser> user = securityRepository.findByUsername(username);
    if (user.isPresent()) {
      throw new SecurityException("User already exists!", null);
    }

    SysUser newUser = new SysUser();
    newUser.setUsername(username);
    newUser.setPassword(passwordEncoder.encode(password));
    newUser.setRoles(null);
  }

  @Override
  public LoginResult authenticate(String username, String password, Set<String> roles) {
    if (Objects.isNull(username)) {
      throw new UsernameNotFoundException("Username cannot be null!");
    }
    var authToken = new UsernamePasswordAuthenticationToken(username, password);
    authManager.authenticate(authToken);
    authToken.setAuthenticated(true);
    String jwt = jwtUtil.generateToken(username, roles);
    return new LoginResult(true, roles, jwt);
  }
}
