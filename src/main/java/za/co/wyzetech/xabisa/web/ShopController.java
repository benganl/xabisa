package za.co.wyzetech.xabisa.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/shop"})
public class ShopController {
  
  @GetMapping(path = {"", "/"})
  public String index(Model model) {
    model.addAttribute("childContent", "shop");
    return "base";
  }

}
