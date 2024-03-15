package za.co.wyzetech.xabisa.security;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;
import za.co.wyzetech.xabisa.core.validators.impl.LoginValidator;
import za.co.wyzetech.xabisa.core.validators.impl.RegistrationValidator;
import za.co.wyzetech.xabisa.security.dto.LoginDto;
import za.co.wyzetech.xabisa.security.dto.RegistrationDto;
import za.co.wyzetech.xabisa.security.exception.SecurityException;
import za.co.wyzetech.xabisa.security.models.LoginResult;
import za.co.wyzetech.xabisa.security.models.Registration;

@Slf4j
@Controller
@RequestMapping(path = {"/"})
class SecurityController {

  private final RegistrationValidator registrationValidator;
  private final LoginValidator loginValidator;
  private final SecurityManager securityManager;
  private final ModelMapper modelMapper;

  public SecurityController(SecurityManager securityManager, ModelMapper modelMapper,
      RegistrationValidator registrationValidator, LoginValidator loginValidator) {
    this.registrationValidator = registrationValidator;
    this.securityManager = securityManager;
    this.modelMapper = modelMapper;
    this.loginValidator = loginValidator;
  }

  @GetMapping(path = {"/register"})
  public String register(Model model) {
    model.addAttribute("childContent", "register");
    return "base";
  }

  @PostMapping(path = {"/register"},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public String registerPost(@RequestBody RegistrationDto data) {
    final List<ValidationResult> validationResults = registrationValidator.validate(data);
    validationResults.forEach((e) -> {
      System.out.println(e);
    });
    final var containsValidationErrors = !validationResults.isEmpty();

    if (containsValidationErrors) {
      log.warn("Validation errors: ", containsValidationErrors);
    } else {
      log.warn("No validation errors");
      Registration registration = modelMapper.map(data, Registration.class);
      try {
        securityManager.createUser(registration);
      } catch (SecurityException e1) {
        e1.printStackTrace();
      }
    }
    return "base";
  }

  @GetMapping(path = {"/login"})
  public String login(Model model) {
    log.info("Registration:::::::");
    model.addAttribute("childContent", "login");
    return "base";
  }

  @PostMapping(path = {"/login"},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public String loginPost(@RequestBody LoginDto data) { // Changed from @ModelAttri
    final List<ValidationResult> validationResults = loginValidator.validate(data);

    final var hasValidationErrors = !validationResults.isEmpty();
    if (hasValidationErrors) {
      log.info("Failed validation!!!");
    } else {
      final String username = data.getUsername();
      final String password = data.getPassword();

      try {
        LoginResult authResult = securityManager.login(username, password);
      } catch (SecurityException e) {
        e.printStackTrace();
      }
    }
    return "base";
  }
}
