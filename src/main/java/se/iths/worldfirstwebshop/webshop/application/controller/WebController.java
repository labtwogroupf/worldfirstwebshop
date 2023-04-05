package se.iths.worldfirstwebshop.webshop.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import se.iths.worldfirstwebshop.webshop.application.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.application.messagequeue.Publisher;
import se.iths.worldfirstwebshop.webshop.application.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.application.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.application.service.InventoryService;
import se.iths.worldfirstwebshop.webshop.application.service.ShopService;


@Controller
public class WebController {



    private final Publisher publisher;
    private final InventoryService inventoryService;

    private final ShopService shopService;

    private final ProductController productController;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public WebController(Publisher publisher, InventoryService inventoryService,
                         ShopService shopService, ProductController productController,
                         ProductRepository repo, InventoryRepository inventoryRepository) {

        this.publisher = publisher;
        this.inventoryService = inventoryService;

        this.shopService = shopService;

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
    @GetMapping("/showCart")
        String showCart(Model model){
            model.addAttribute("cart", shopService.getCart());
            return "carts";
        }

    @GetMapping("/showCartForm")
    String showCart(Model model, ProductDto productDto, InventoryDto inventoryDto){
        model.addAttribute("allInventories", inventoryRepository.findAll());
        model.addAttribute("inventory", inventoryDto);
        model.addAttribute("product", productDto);
        return "addToCartForm";
    }
    @PostMapping("/addToCart")
    String addToCart(Model model,ProductDto productDto, int amount){
        model.addAttribute("cart", shopService.getCart());
        publisher.addToQueue(productDto,amount);

        return "carts";
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
    String addNew(ProductDto productDto, Model model) {
        productDto.setId(null);
        productController.addProduct(productDto);
        model.addAttribute("allProducts", productRepository.findAll());
        return "products";
    }

    @GetMapping("/showAddToInventoryForm")
    String addToInventoryForm(Model model, ProductDto productDto, InventoryDto inventoryDto) {
        model.addAttribute("allProducts", productRepository.findAll());
        model.addAttribute("product", productDto);
        model.addAttribute("inventory", inventoryDto);
        return "addNewProductToInventory";

    }
    @PostMapping("/addProductToInventory")
    String addProductToInventory(Model model, ProductDto productDto,int amount) {
        inventoryService.addProduct(productDto.getId(),amount);
        model.addAttribute("allInventories", inventoryRepository.findAll());
        return "inventories";
    }

    @PostMapping("/checkout")
    String checkout(){
        if(!shopService.getCart().isEmpty()){
            return "carts";
        }
        shopService.checkout();
        return "mainSite";
    }


}
