package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.entities.InventoryEntity;

import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    InventoryRepository inventoryRepo;
    ProductRepository productRepo;
    Mapper mapper;

    public InventoryController(InventoryRepository repo, ProductRepository productRepo, Mapper mapper) {
        this.inventoryRepo = repo;
        this.productRepo = productRepo;
        this.mapper = mapper;

    }

    @PutMapping("/subtract/{id}")
    public void updateWhenSold(@PathVariable Long id, @RequestParam int amount) {
        inventoryRepo.findById(id).ifPresent(inventoryEntity -> inventoryEntity.setAmount(inventoryEntity.getAmount() - amount));
    }

    @PutMapping("/add/{id}")
    public void updateAddToStock(@PathVariable Long id, @RequestParam int amount) {
        inventoryRepo.findById(id).ifPresent(inventoryEntity -> inventoryEntity.setAmount(inventoryEntity.getAmount() + amount));
    }

    @PostMapping()
    public void addProduct(@RequestParam("id") Long productId, int amount) {
        var product = productRepo.findById(productId);
        if (product.isPresent()) {
            var inventoryEntity = new InventoryEntity();
            inventoryEntity.setProduct(product.get());
            inventoryEntity.setAmount(amount);
            inventoryRepo.save(inventoryEntity);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void removeById(@PathVariable Long id) {
        if (inventoryRepo.findById(id).isPresent())
            inventoryRepo.deleteById(id);
    }

    @GetMapping("/products")
    public List<InventoryDto> getProducts() {
        return inventoryRepo.findAll().stream().map(inventoryEntity -> mapper.mapToInventoryDto(inventoryEntity)).toList();
    }

}











