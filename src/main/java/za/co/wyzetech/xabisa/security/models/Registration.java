package za.co.wyzetech.xabisa.security.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class Registration implements Serializable {

  private static final long serialVersionUID = 1L;

  private String email;
  private String mobileNumber;
  private String firstName;
  private String lastName;
  private String password;
  private String idType;
  private String idNumber;
  private boolean createCheck;
  private boolean checkTrade;
  private boolean privacyCheck;

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Registration [email=");
    builder.append(email);
    builder.append(", mobileNumber=");
    builder.append(mobileNumber);
    builder.append(", firstName=");
    builder.append(firstName);
    builder.append(", lastName=");
    builder.append(lastName);
    builder.append(", password=");
    builder.append(" ---[REDACTED]--- ");
    builder.append(", idType=");
    builder.append(idType);
    builder.append(", idNumber=");
    builder.append(idNumber);
    builder.append(", createCheck=");
    builder.append(createCheck);
    builder.append(", checkTrade=");
    builder.append(checkTrade);
    builder.append(", privacyCheck=");
    builder.append(privacyCheck);
    builder.append("]");
    return builder.toString();
  }


}
