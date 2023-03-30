package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.context.annotation.ComponentScan;
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
    public void updateWhenSold(@PathVariable Long id, @RequestParam int amount) {
        service.updateWhenSold(id, amount);
    }

    @PutMapping("/add/{id}")
    public void updateAddToStock(@PathVariable Long id, @RequestParam int amount) {
        service.updateAddToStock(id, amount);
    }

    @PostMapping()
    public void addProduct(@RequestParam("id") Long productId, int amount) {
        service.addProduct(productId, amount);
    }

    @DeleteMapping("/delete")
    public void removeAll() {
        service.removeAll();
    }

    @DeleteMapping("/delete/{id}")
    public void removeById(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("/products")
    public List<InventoryDto> getProducts() {
        return service.getProducts();
    }

}











