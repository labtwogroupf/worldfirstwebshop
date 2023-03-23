package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.product.Product;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    Shop shop;

    public ShopController(Shop shop) {
        this.shop = shop;
    }

    @PostMapping
    void addToCart(@RequestBody Product product, int amount) {
        shop.addToCart(product, amount);
    }

    @PostMapping
    void removeFromCart(@RequestBody Product product) {
        shop.removeFromCart(product);
    }

    @PostMapping
    void checkout() {
        shop.checkout();
    }

}
