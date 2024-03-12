package za.co.wyzetech.xabisa.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/security"})
public class SecurityController {
  @GetMapping(path = {"/register"})
  public String register(Model model) {
    model.addAttribute("childContent", "register");
    return "base";
  }

  @GetMapping(path = {"/login"})
  public String login(Model model) {
    model.addAttribute("childContent", "login");
    return "base";
  }
}
