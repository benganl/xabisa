package za.co.wyzetech.xabisa.modules.contact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/contact"})
public class ContactController {
  @GetMapping(path = {"", "/"})
  public String contact(Model model) {
    model.addAttribute("childContent", "contact");
    return "base";
  }
}
