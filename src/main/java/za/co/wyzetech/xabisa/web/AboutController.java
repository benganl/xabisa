package za.co.wyzetech.xabisa.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/about"})
public class AboutController {
  
  @GetMapping(path = {"", "/"})
  public String index(Model model) {
    model.addAttribute("childContent", "about");
    return "base";
  }

}
