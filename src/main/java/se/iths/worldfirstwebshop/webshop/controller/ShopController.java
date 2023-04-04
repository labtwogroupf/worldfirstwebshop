package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.messageQueue.Publisher;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.service.ShopService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    ShopService service;
    Publisher publisher;

    public ShopController(ShopService service, Publisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @PutMapping("/add")
    ResponseEntity addToCart(@RequestBody ProductDto product, @RequestParam int amount) {
        publisher.addToQueue(product, amount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/remove")
    ResponseEntity removeFromCart(@RequestBody ProductDto product) {
        service.removeFromCart(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/checkout")
    ResponseEntity checkout() {
        service.checkout();
        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @GetMapping("/getAll")
    List<Map.Entry<Product, Integer>> getCart(){
        return service.getCart();
    }
}
