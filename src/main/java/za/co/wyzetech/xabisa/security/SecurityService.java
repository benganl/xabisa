package za.co.wyzetech.xabisa.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {

  String generateToken(String username);

  Boolean validateToken(String token, String username);

  String getUsernameFromToken(String token);

  Boolean isTokenExpired(String token);

  Authentication authenticate(String username, String password);

}
