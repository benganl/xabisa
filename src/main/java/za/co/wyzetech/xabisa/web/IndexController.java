package za.co.wyzetech.xabisa.web;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"", "/"})
public class IndexController {

  /**
   * The application landing page.
   */
  @GetMapping(path = {"", "/index"})
  public String index(Model model) {
    model.addAttribute("childContent", "index");
    return "base";
  }

  /**
   * This is to lend the user landing page
   * 
   * @param model spring mvp model
   * @param principal authenticated user.
   */
  @GetMapping(path = {"/home"})
  public String home(Model model, Principal principal) {
    model.addAttribute("childContent", "home");
    return "base";
  }
}
