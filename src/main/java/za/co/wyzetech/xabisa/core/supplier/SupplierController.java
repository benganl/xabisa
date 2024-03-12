package za.co.wyzetech.xabisa.core.supplier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * This would be nice in a Microservice Arch.
 * 
 * @deprecated
 * @hidden
 * @return
 * 
@Controller(value = "supplierController")
@RequestMapping(path = "/supplier")
public class SupplierController {

  @GetMapping(path = {"", "/"})
  public String index(Model model) {
    return "index";
  }
}
*/