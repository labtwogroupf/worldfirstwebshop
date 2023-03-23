package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }
    @GetMapping("/{id}")
    ProductEntity getAProduct(@PathVariable long id){
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    void addProduct(@RequestBody ProductEntity product){
        repo.save(product);
    }
    @DeleteMapping("/{id}")
    void removeProductById(@PathVariable long id) {
        repo.deleteById(id);
    }


}
