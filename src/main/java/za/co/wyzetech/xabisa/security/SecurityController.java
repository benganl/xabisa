package za.co.wyzetech.xabisa.security;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;
import za.co.wyzetech.xabisa.core.validators.impl.RegistrationValidator;
import za.co.wyzetech.xabisa.security.dto.RegistrationDto;
import za.co.wyzetech.xabisa.security.models.Registration;

@Slf4j
@Controller
@RequestMapping(path = {"/"})
class SecurityController {

  private final RegistrationValidator registrationValidator;
  private final SecurityManager securityManager;
  private final ModelMapper modelMapper;

  public SecurityController(RegistrationValidator registrationValidator,
      SecurityManager securityService, ModelMapper modelMapper) {
    this.registrationValidator = registrationValidator;
    this.securityManager = securityService;
    this.modelMapper = modelMapper;
  }

  @GetMapping(path = {"/register"})
  public String register(Model model) {
    model.addAttribute("childContent", "register");
    return "base";
  }

  @PostMapping(path = {"/register"},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public String registerPost(Model model, @RequestBody RegistrationDto registrationDto) {
    final List<ValidationResult> validationResults =
        registrationValidator.validate(registrationDto);
    validationResults.forEach((e) -> {
      System.out.println(e);
    });
    final var containsValidationErrors = !validationResults.isEmpty();

    if (containsValidationErrors) {
      log.warn("Validation errors: ", containsValidationErrors);
    } else {
      log.warn("No validation errors");
      Registration registration = modelMapper.map(registrationDto, Registration.class);
      securityManager.createUser(registration);
    }

    model.addAttribute("childContent", "login");
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
  public String loginPost(Model model, @ModelAttribute RegistrationDto registration) {
    log.info("Registration::::::: {}", registration.toString());
    model.addAttribute("childContent", "login");
    return "base";
  }
}
