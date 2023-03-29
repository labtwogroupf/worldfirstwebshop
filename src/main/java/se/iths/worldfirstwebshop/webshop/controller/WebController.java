package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;

@Controller
public class WebController {

    private final ProductRepository repo;

    public WebController(ProductRepository repo) {
        this.repo = repo;
    }
    @GetMapping("/showProducts")
    String products (Model model){
        model.addAttribute("allProducts",repo.findAll());
        return "products";

    }
}
