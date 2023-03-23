package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductRepository repo;
    Mapper mapper;

    public ProductController(ProductRepository repo, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    ProductDto getAProduct(@PathVariable long id) {
        return mapper.mapToDto(repo.findById(id).orElseThrow());
    }

    @PostMapping
    void addProduct(@RequestBody ProductDto product) {
        repo.save(mapper.mapToEntity(product));
    }

    @DeleteMapping("/{id}")
    void removeProductById(@PathVariable long id) {
        repo.deleteById(id);
    }
}
