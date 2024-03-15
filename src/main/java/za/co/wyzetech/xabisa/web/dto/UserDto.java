package za.co.wyzetech.xabisa.web.dto;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;

@Data
public class UserDto implements Serializable {
  private static final long serialVersionUID = 1L;
  private String username;
  private String jwt;
  private Set<String> roles;
  private boolean loggedIn;
}