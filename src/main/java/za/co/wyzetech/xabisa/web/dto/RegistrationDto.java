package za.co.wyzetech.xabisa.web.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class RegistrationDto implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private String email;
  private String mobileNumber;
  private String firstName;
  private String lastName;
  private String password;
  private String idType;
  private String idNumber;
  private Boolean createCheck;
  private Boolean checkTrade;
  private Boolean privacyCheck;

}
