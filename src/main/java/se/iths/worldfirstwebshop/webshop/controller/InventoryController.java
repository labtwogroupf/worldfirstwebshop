package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.service.InventoryService;

import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PutMapping("/subtract/{id}")
    ResponseEntity updateWhenSold(@PathVariable Long id, @RequestParam int amount) {
        service.updateWhenSold(id, amount);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add/{id}")
    ResponseEntity updateAddToStock(@PathVariable Long id, @RequestParam int amount) {
        service.updateAddToStock(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    ResponseEntity addProduct(@RequestParam("id") Long productId, @RequestParam int amount) {
        service.addProduct(productId, amount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    ResponseEntity removeAll() {
        service.removeAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity removeById(@PathVariable Long id) {
        service.removeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    List<InventoryDto> getProducts() {
        return service.getProducts();
    }
}
