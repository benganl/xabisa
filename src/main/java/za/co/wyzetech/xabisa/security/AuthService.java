package za.co.wyzetech.xabisa.security;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.security.exception.SecurityException;
import za.co.wyzetech.xabisa.security.models.LoginResult;

public interface AuthService extends UserDetailsService {
  LoginResult login(String username, String password) throws SecurityException;
}

@Slf4j
@Service
class DefaultAuthenticationService implements AuthService {
  private final SecurityRepository securityRepository;
  private final PasswordEncoder passwordEncoder;

  public DefaultAuthenticationService(SecurityRepository securityRepository,
      PasswordEncoder passwordEncoder) {
    this.securityRepository = securityRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public LoginResult login(String username, String password) throws SecurityException {
    if (Objects.isNull(username)) {
      throw new SecurityException("Username must not be empty!", SecurityException.USER_NAME_NULL);
    }

    if (Objects.isNull(password)) {
      throw new SecurityException("Passoword must not be empty!", SecurityException.PASSWORD_NULL);
    }

    try {
      Optional<SysUser> user = securityRepository.findByUsername(username);
      if (user.isEmpty()) {
        throw new SecurityException("Invalid credentials", 10064);
      }

      SysUser sysUser = user.get();

      if (!passwordEncoder.matches(password, sysUser.getPassword())) {
        throw new SecurityException("Invalid credentials", 10064);
      }

      Set<String> roles =
          sysUser.getRoles().stream().map(SysRole::getName).collect(Collectors.toSet());

      return new LoginResult(true, roles);
    } catch (BadCredentialsException ex) {
      log.warn("Bad credentials failed for user: {}. Problem: {}", username, ex.getMessage());
      throw new SecurityException("Bad credentials supplied.", 10001);
    } catch (DisabledException ex) {
      log.warn("Disabled account exception: {}. Problem: {}", username, ex.getMessage());
      throw new SecurityException("Account disabled.", 10002);
    } catch (LockedException ex) {
      log.warn("Locked account exception: {}. Problem: {}", username, ex.getMessage());
      throw new SecurityException("Account locked.", 10003);
    } catch (AuthenticationException ex) {
      log.warn("Authentication exception: {}. Problem: {}", username, ex.getMessage());
      throw new SecurityException("Issue with authentication user. Please contact support.", 10004);
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (Objects.isNull(username)) {
      throw new UsernameNotFoundException("Username is null!!!");
    }

    SysUser user = securityRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Unable to find user."));

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), getAuthorities(user.getRoles()));
  }

  private Set<GrantedAuthority> getAuthorities(Set<SysRole> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toSet());
  }
}
