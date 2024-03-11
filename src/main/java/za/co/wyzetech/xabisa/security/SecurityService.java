package za.co.wyzetech.xabisa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService extends UserDetailsService {

  String generateToken(String username);

  Boolean validateToken(String token, String username);

  String getUsernameFromToken(String token);

  Boolean isTokenExpired(String token);

  Authentication authenticate(String username, String password);

  void createUser(String username, String password);

  User findByUsername(String username);
}
