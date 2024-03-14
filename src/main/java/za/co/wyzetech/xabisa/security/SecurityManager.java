package za.co.wyzetech.xabisa.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import za.co.wyzetech.xabisa.security.models.Registration;

public interface SecurityManager extends UserDetailsService {
  void createUser(Registration registration);
}
