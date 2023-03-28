package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.service.ShopService;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @PutMapping("/add")
    void addToCart(@RequestBody ProductDto product, int amount) {
        service.addToCart(product, amount);
    }

    @PutMapping("/remove")
    void removeFromCart(@RequestBody ProductDto product) {
        service.removeFromCart(product);
    }

    @PutMapping("/checkout")
    void checkout() {
        service.checkout();

    }

}
