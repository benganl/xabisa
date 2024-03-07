package za.co.wyzetech.xabisa.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "customerController")
@RequestMapping(path = "/customer")
public class CustomerController {

  @GetMapping(path = {"", "/"})
  public String index(Model model) {
    return "index";
  }
}
