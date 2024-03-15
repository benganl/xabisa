package za.co.wyzetech.xabisa.core.validators.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import za.co.wyzetech.xabisa.core.validators.ObjectValidator;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;
import za.co.wyzetech.xabisa.security.dto.LoginDto;

@Component
public class LoginValidator implements ObjectValidator<LoginDto> {


  private final PasswordValidator passwordValidator;
  private final ValueValidator valueValidator;

  public LoginValidator(PasswordValidator passwordValidator, ValueValidator valueValidator) {
    this.passwordValidator = passwordValidator;
    this.valueValidator = valueValidator;
  }

  @Override
  public List<ValidationResult> validate(final LoginDto data) {
    final List<ValidationResult> errors = new ArrayList<ValidationResult>();

    final String username = data.getUsername();
    final String password = data.getPassword();

    ValidationResult validationResult = new ValidationResult();

    validationResult = valueValidator.validate("username", username, false, 5, 20);
    if (validationResult.hasErrors())
      errors.add(validationResult);

    validationResult = passwordValidator.validate("password", password, false, 6, 32);
    if (validationResult.hasErrors())
      errors.add(validationResult);

    return errors;
  }

}
