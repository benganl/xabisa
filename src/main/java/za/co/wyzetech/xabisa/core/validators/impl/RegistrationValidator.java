package za.co.wyzetech.xabisa.core.validators.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import za.co.wyzetech.xabisa.core.validators.ObjectValidator;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;
import za.co.wyzetech.xabisa.security.dto.RegistrationDto;

@Component
public class RegistrationValidator implements ObjectValidator<RegistrationDto> {

  private final EmailValidator emailValidator;
  private final ValueValidator nameValidator;
  private final RsaIdValidator rsaIdValidator;
  private final PasswordValidator passwordValidator;

  RegistrationValidator(EmailValidator emailValidator, ValueValidator nameValidator,
      RsaIdValidator rsaIdValidator, PasswordValidator passwordValidator) {
    this.emailValidator = emailValidator;
    this.nameValidator = nameValidator;
    this.rsaIdValidator = rsaIdValidator;
    this.passwordValidator = passwordValidator;
  }

  public List<ValidationResult> validate(RegistrationDto registration) {

    final var validation = new ArrayList<ValidationResult>();
    var validationResult = new ValidationResult();

    String email = registration.getEmail();
    validationResult = emailValidator.validate("email", email, false, 5, 30);
    if (validationResult.hasErrors())
      validation.add(validationResult);

    String firstName = registration.getFirstName();
    validationResult = nameValidator.validate("firstName", firstName, true, 2, 20);
    if (validationResult.hasErrors())
      validation.add(validationResult);

    String idNumber = registration.getIdNumber();
    validationResult = rsaIdValidator.validate("idNumber", idNumber, false, 13, 13);
    if (validationResult.hasErrors())
      validation.add(validationResult);

    String idType = registration.getIdType();
    validationResult = nameValidator.validate("idType", idType, true, 2, 5);
    if (validationResult.hasErrors())
      validation.add(validationResult);

    String lastName = registration.getLastName();
    validationResult = nameValidator.validate("lastName", lastName, true, 1, 20);
    if (validationResult.hasErrors())
      validation.add(validationResult);

    String mobileNumber = registration.getMobileNumber();
    validationResult = nameValidator.validate("mobileNumber", mobileNumber, false, 10, 13);
    if (validationResult.hasErrors())
      validation.add(validationResult);

    String password = registration.getPassword();
    validationResult = passwordValidator.validate("password", password, false, 6, 128);
    if (validationResult.hasErrors())
      validation.add(validationResult);

    // todo boolean tradeOptIn = registration.isCheckTrade();

    // todo boolean signUpConsent = registration.isCreateCheck();

    // todo boolean checkPrivacy = registration.isPrivacyCheck();

    return validation;
  }


}
