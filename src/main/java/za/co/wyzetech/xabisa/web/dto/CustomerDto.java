package za.co.wyzetech.xabisa.web.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class CustomerDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String email;
  private String mobile;
  private String lastName;
  private String firstName;
  private String password;
}
