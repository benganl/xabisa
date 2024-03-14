package za.co.wyzetech.xabisa.core.validators.impl;

import org.springframework.stereotype.Component;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;
import za.co.wyzetech.xabisa.core.validators.DataValidator;
import za.co.wyzetech.xabisa.security.dto.LoginDto;

@Component
public class LoginValidator implements DataValidator<LoginDto> {
  @Override
  public ValidationResult validate(String property, LoginDto t, boolean nullable, int min, int max) {
    throw new RuntimeException("Not implemented!!!");
  }
}
