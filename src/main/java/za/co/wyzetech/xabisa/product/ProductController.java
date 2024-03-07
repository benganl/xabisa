package za.co.wyzetech.xabisa.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "productController")
@RequestMapping(path = "/product")
public class ProductController {

  @GetMapping(path = {"", "/"})
  public String index(Model model) {
    return "index";
  }
}
