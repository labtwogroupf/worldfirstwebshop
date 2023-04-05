package se.iths.worldfirstwebshop.webshop.application.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.application.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.application.messagequeue.Publisher;
import se.iths.worldfirstwebshop.webshop.domain.product.Product;
import se.iths.worldfirstwebshop.webshop.application.service.ShopService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    Mapper mapper;
    ShopService service;
    Publisher publisher;

    public ShopController(ShopService service, Publisher publisher, Mapper mapper) {
        this.mapper = mapper;
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
    @GetMapping("/getAll")
    List<Map.Entry<Product, Integer>> getCart(){
        return service.getCart();
    }
}
