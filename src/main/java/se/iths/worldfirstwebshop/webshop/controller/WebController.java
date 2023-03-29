package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;



@Controller
public class WebController {


    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public WebController(ProductRepository repo, InventoryRepository inventoryRepository) {
        this.productRepository = repo;
        this.inventoryRepository = inventoryRepository;
    }
    @GetMapping("/showProducts")
    String products (Model model){
        model.addAttribute("allProducts", productRepository.findAll());
        return "products";

    }
    @GetMapping("/showInventory")
    String inventories (Model model){
        model.addAttribute("allInventories", inventoryRepository.findAll());
        return "inventories";
    }
    @GetMapping("/mainSite")
    String mainSite (){
        return "mainSite";
    }
}