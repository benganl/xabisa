package za.co.wyzetech.xabisa.security.models;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import lombok.Data;

@Data
public class LoginResult implements Serializable {
  private static final long serialVersionUID = 1L;

  private final Set<String> roles = new TreeSet<String>();

  private String username;
  private String jwt;
  private boolean loggedIn = false;

  public LoginResult() {}

  public LoginResult(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  public LoginResult(boolean loggedIn, Set<String> roles) {
    this.loggedIn = loggedIn;
    this.roles.addAll(roles);
  }

  public LoginResult(boolean loggedIn, Set<String> roles, String jwt) {
    this.loggedIn = loggedIn;
    this.roles.addAll(roles);
    this.jwt = jwt;
  }
}
