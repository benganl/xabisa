package za.co.wyzetech.xabisa.core.validators.impl;

import java.util.Objects;
import org.springframework.stereotype.Component;
import za.co.wyzetech.xabisa.core.validators.DataValidator;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;

@Component
public class PasswordValidator implements DataValidator<String> {

  private final String PASSWORD_PATTERN =
      "(?=^.{12,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*!()])(?=.*\\W{1,})";

  @Override
  public ValidationResult validate(String property, String password, boolean nullable, int minLength,
      int maxLength) {
    ValidationResult validationResult = new ValidationResult();
    boolean isNullPassword = Objects.isNull(password);

    if (isNullPassword && !nullable) {
      validationResult.add(property, "Password cannot be null.");
    } else {
      int passwordLength = password.length();

      if (passwordLength < minLength) {
        validationResult.add(property,
            String.format("Password must be at least %d characters long.", minLength));
      }

      if (passwordLength > maxLength) {
        validationResult.add(property,
            String.format("Password exceeds maximum length of %d characters.", maxLength));
      }

      if (password.matches(PASSWORD_PATTERN)) {
        validationResult.add(property, "Password must meet complexity requirements.");
      }
    }

    return validationResult;
  }
}
