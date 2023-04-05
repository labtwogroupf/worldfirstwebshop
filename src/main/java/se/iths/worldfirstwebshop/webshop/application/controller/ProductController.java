package se.iths.worldfirstwebshop.webshop.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.application.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    ProductDto getAProduct(@PathVariable Long id) {
        return service.getAProduct(id);
    }

    @PostMapping
    ResponseEntity addProduct(@RequestBody ProductDto product) {
        service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
