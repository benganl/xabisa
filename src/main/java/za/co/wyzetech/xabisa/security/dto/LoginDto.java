package za.co.wyzetech.xabisa.security.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class LoginDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;

  public LoginDto() {}

  public LoginDto(String username, String passoword) {
    super();
    this.username = username;
    this.password = passoword;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("LoginDto [username=");
    builder.append(username);
    builder.append(", passoword=");
    builder.append("[ *** REDACTED ***  ]");
    builder.append("]");
    return builder.toString();
  }
}
