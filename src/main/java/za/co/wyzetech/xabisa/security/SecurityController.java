package za.co.wyzetech.xabisa.security;

import java.io.Serializable;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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
class SecurityController implements Serializable {

  private static final long serialVersionUID = 1L;
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

  @PostMapping(path = {"/login"}, consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> loginPost(@RequestBody LoginDto loginData) {
    List<ValidationResult> validationErrors = loginValidator.validate(loginData);
    if (!validationErrors.isEmpty()) {
      return ResponseEntity.badRequest()
          .body(new ApiError(HttpStatus.BAD_REQUEST, "Validation errors", validationErrors));
    }

    // Login Logic
    try {
      LoginResult loginResult =
          securityManager.login(loginData.getUsername(), loginData.getPassword());
      return ResponseEntity.ok(loginResult);
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (Exception e) {
      log.error("Unexpected login error", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
