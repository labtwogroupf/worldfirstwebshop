package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;


@Controller
public class WebController {

    private final InventoryController inventoryController;
    private final ProductController productController;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public WebController(InventoryController inventoryController, ProductController productController, ProductRepository repo, InventoryRepository inventoryRepository) {
        this.inventoryController = inventoryController;
        this.productController = productController;
        this.productRepository = repo;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/showProducts")
    String products(Model model) {
        model.addAttribute("allProducts", productRepository.findAll());
        return "products";

    }

    @GetMapping("/showInventory")
    String inventories(Model model) {
        model.addAttribute("allInventories", inventoryRepository.findAll());
        return "inventories";
    }

    @GetMapping("/mainSite")
    String mainSite() {
        return "mainSite";
    }

    @GetMapping("/showProductForm")
    String showProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "addNewProductForm";

    }

    @PostMapping("/addProduct")
    String addNew(ProductDto productDto, BindingResult bindingResult, Model model) {
        productDto.setId(null);
        productController.addProduct(productDto);
        model.addAttribute("allProducts", productRepository.findAll());
        return "products";
    }

    @GetMapping("/addToInventory")
    String addToInventory(Model model, ProductDto productDto) {
        model.addAttribute("allProducts", productRepository.findAll());
        model.addAttribute("product", productDto);

        return "addNewProductToInventory";

    }
    @PostMapping("/addProductToInventory")
    String addProductToInventory(Model model, InventoryEntity inventoryEntity){
        model.addAttribute("products", inventoryEntity.getAmount());
        return "inventories";
    }


}
