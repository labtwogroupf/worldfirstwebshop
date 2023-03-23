package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    InventoryRepository inventoryRepo;
    ProductRepository productRepo;

    public InventoryController(InventoryRepository repo) {
        this.inventoryRepo = repo;
    }

    @PutMapping("/subtract/{id}")
    public void updateWhenSold(@PathVariable int amount, Long id){
        inventoryRepo.findById(id).ifPresent(inventoryEntity -> inventoryEntity.setAmount(inventoryEntity.getAmount() - amount));
    }

    @PutMapping("/add/{id}")
    public void updateAddToStock(@PathVariable int amount, Long id){
        inventoryRepo.findById(id).ifPresent(inventoryEntity -> inventoryEntity.setAmount(inventoryEntity.getAmount() + amount));
    }

    @PostMapping()
    public void addProduct(@RequestParam("id") Long productId, int amount){
        var product = productRepo.findById(productId);
        if (product.isPresent()){
         var inventoryEntity = new InventoryEntity();
         inventoryEntity.setProduct(product.get());
         inventoryEntity.setAmount(amount);
         inventoryRepo.save(inventoryEntity);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void removeById(@PathVariable Long id){
        if(inventoryRepo.findById(id).isPresent())
            inventoryRepo.deleteById(id);
    }











}
