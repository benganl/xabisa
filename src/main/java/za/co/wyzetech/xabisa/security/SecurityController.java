package za.co.wyzetech.xabisa.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"", "/"})
public class SecurityController {

  @GetMapping(path = "login")
  public String login(Model model) {
    return "login";
  }

  @GetMapping(path = "logout")
  public String logout(Model model) {
    return "logout";
  }

  @GetMapping(path = "signup")
  public String signup(Model model) {
    return "signup";
  }

  @PostMapping(path = "auth")
  public String authenticate(Model model) {
    return "index";
  }
}
