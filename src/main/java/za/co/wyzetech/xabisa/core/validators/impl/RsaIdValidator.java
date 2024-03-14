package za.co.wyzetech.xabisa.core.validators.impl;

import java.util.Objects;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import za.co.wyzetech.xabisa.core.validators.DataValidator;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;

@Component
public class RsaIdValidator implements DataValidator<String> {

  @Override
  public ValidationResult validate(String property, String idNumber, boolean nullable, int min,
      int max) {
    var validationResult = new ValidationResult();
    var isNullIdNumber = Objects.isNull(idNumber);

    if (isNullIdNumber && !nullable) {
      validationResult.add(property, "Id number is null {}");
    } else {
      if (!isValidIdNumber(idNumber)) {
        validationResult.add(property, String.format("%s is not a valid Id Number.", idNumber));
      }
    }

    return validationResult;
  }

  private boolean isValidIdNumber(String idNumber) {
    if (Objects.isNull(idNumber))
      return false;

    if (!Pattern.matches("^\\d{13}$", idNumber)) {
      return false;
    }

    int sum = 0;
    int[] weights = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
    for (int i = 0; i < 12; i++) {
      int digit = Character.getNumericValue(idNumber.charAt(i));
      int weighted = digit * weights[i];
      if (weighted > 9) {
        weighted -= 9;
      }
      sum += weighted;
    }

    int checksum = 10 - (sum % 10);
    if (checksum == 10) {
      checksum = 0;
    }

    int lastDigit = Character.getNumericValue(idNumber.charAt(12));
    return checksum == lastDigit;
  }
}
