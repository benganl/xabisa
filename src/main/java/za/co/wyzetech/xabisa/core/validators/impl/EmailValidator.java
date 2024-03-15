package za.co.wyzetech.xabisa.core.validators.impl;

import java.util.Objects;
import org.springframework.stereotype.Component;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;
import za.co.wyzetech.xabisa.core.validators.DataValidator;

@Component
public class EmailValidator implements DataValidator<String> {

  private final org.apache.commons.validator.routines.EmailValidator validator;

  public EmailValidator() {
    validator = org.apache.commons.validator.routines.EmailValidator.getInstance();
  }
  
  @Override
  public ValidationResult validate(String property, String email, boolean nullable, int min, int max) {
    var validationResult = new ValidationResult();
    
    if(Objects.isNull(email)) {
      validationResult.add(property, "Email is required!");
    }
    
    if(!validator.isValid(email)) {
      validationResult.add(property, String.format("%s is not a valid email!", email));
    }
    
    return validationResult;
  }
}
