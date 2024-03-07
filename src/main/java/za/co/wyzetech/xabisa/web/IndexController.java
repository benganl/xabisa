package za.co.wyzetech.xabisa.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"", "/"})
public class IndexController {

  @GetMapping
  public String index(Model model) {
    model.addAttribute("fragName", "fragments/customers/index");
    return "index";
  }
}
