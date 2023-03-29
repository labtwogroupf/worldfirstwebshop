package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity addToCart(@RequestBody ProductDto product, int amount) {
        service.addToCart(product, amount);
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

}
