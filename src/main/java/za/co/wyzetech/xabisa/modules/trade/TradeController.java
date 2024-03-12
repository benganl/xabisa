package za.co.wyzetech.xabisa.modules.trade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/trade"})
public class TradeController {

  @GetMapping(path = {"", "/"})
  public String trade(Model model) {
    model.addAttribute("childContent", "trade");
    return "base";
  }

}
