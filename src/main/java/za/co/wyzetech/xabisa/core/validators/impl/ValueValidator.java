package za.co.wyzetech.xabisa.core.validators.impl;

import java.util.Objects;
import org.springframework.stereotype.Component;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;
import za.co.wyzetech.xabisa.core.validators.DataValidator;

@Component
public class ValueValidator implements DataValidator<String> {
  private static final String VALUE_PATTERN = "^[\\p{L}\\s'\\-.,\\+]$";

  @Override
  public ValidationResult validate(String property, String value, boolean nullable, int minValue, int maxValue) {
    final var validationResult = new ValidationResult();
    final var isNull = Objects.isNull(value);

    if (isNull && !nullable) {
      validationResult.add(property, String.format("%s cannot be null!!!", property));
    } else {
      if (!isNull && value.length() < minValue) {
        validationResult.add(property, String
            .format("[%s] has a value of %s. Must not be less than [%s]", property, value, minValue));
      }

      if (!isNull && value.length() > maxValue) {
        validationResult.add(property, String
            .format("[%s] has a value of %s. Must not be more than [%s]", property, value, maxValue));
      }

      if (!isNull && value.matches(VALUE_PATTERN)) {
        validationResult.add(property, String.format("%s does not seem to be a a valid value %s.", property, value));
      }
    }
    return validationResult;
  }
}
