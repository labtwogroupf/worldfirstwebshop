package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
