package za.co.wyzetech.xabisa.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.wyzetech.xabisa.security.exception.SecurityException;
import za.co.wyzetech.xabisa.security.models.Registration;

@Service
class DefaultSecurityManager implements SecurityManager {

  private final SecurityRepository securityRepository;
  private final PasswordEncoder passwordEncoder;

  public DefaultSecurityManager(SecurityRepository securityRepository,
      PasswordEncoder passwordEncoder) {
    this.securityRepository = securityRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void createUser(final Registration registration) {
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
      throw new SecurityException("Passoword must not be empty!", SecurityException.PASSWORD_NULL);
    }

    SysUser newUser = new SysUser();
    newUser.setUsername(username);
    newUser.setPassword(passwordEncoder.encode(password));
    newUser.setRoles(null);

    securityRepository.save(newUser);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SysUser user = securityRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User not found with username: " + username));
    if (Objects.nonNull(user)) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
          user.getPassword(), getAuthorities(user.getRoles()));
    } else {
      throw new UsernameNotFoundException("Incorrect and/or invalid credentials");
    }
  }

  private Set<GrantedAuthority> getAuthorities(Set<SysRole> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toSet());
  }
}
