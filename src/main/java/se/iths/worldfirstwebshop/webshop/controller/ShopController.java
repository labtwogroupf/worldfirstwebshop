package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.messagequeue.Publisher;
import se.iths.worldfirstwebshop.webshop.service.ShopService;

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
    ResponseEntity<Void> addToCart(@RequestBody ProductDto product, @RequestParam int amount) {
        publisher.addToQueue(product, amount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/remove")
    ResponseEntity<Void> removeFromCart(@RequestBody ProductDto product) {
        service.removeFromCart(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/checkout")
    ResponseEntity<Void> checkout() {
        service.checkout();
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
