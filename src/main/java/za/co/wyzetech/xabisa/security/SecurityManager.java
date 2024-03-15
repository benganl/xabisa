package za.co.wyzetech.xabisa.security;

import java.util.Collections;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.security.exception.SecurityException;
import za.co.wyzetech.xabisa.security.models.LoginResult;
import za.co.wyzetech.xabisa.security.models.Registration;

public interface SecurityManager {
  void createUser(Registration registration) throws SecurityException;

  LoginResult login(String username, String password) throws SecurityException;
}


@Slf4j
@Service
class DefaultSecurityManager implements SecurityManager {

  private final PasswordEncoder passwordEncoder;
  private final SecurityService securityService;
  private final AuthService authService;

  public DefaultSecurityManager(SecurityService securityService, AuthService loginService,
      PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.securityService = securityService;
    this.authService = loginService;
  }

  @Override
  public void createUser(final Registration registration) throws SecurityException {
    final var username = registration.getEmail();
    final var password = registration.getPassword();
    final var mobileNumber = registration.getMobileNumber();

    if (Objects.isNull(username)) {
      throw new SecurityException("Username must not be empty!", SecurityException.USER_NAME_NULL);
    }

    if (Objects.isNull(password)) {
      throw new SecurityException("Passoword must not be empty!", SecurityException.PASSWORD_NULL);
    }

    if (Objects.isNull(mobileNumber)) {
      throw new SecurityException("Mobile/Cell number must not be empty!",
          SecurityException.MOBILE_NUMBER_NULL);
    }
    
    securityService.createUser(registration);
  }

  @Override
  public LoginResult login(final String username, final String password) throws SecurityException {
    if(Objects.isNull(username)) throw new SecurityException("Username cannot be null!", 1000234);
    if(Objects.isNull(password)) throw new SecurityException("Password cannot be null!", 1000235);
    
    final var authResult = authService.login(username, password);
    final var roles = Collections.unmodifiableSet(authResult.getRoles());
    
    return securityService.authenticate(username, password, roles);
  }
}
