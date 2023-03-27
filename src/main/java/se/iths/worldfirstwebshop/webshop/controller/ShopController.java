package se.iths.worldfirstwebshop.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    Shop shop;
    Mapper mapper;
    InventoryRepository inventoryRepo;

    public ShopController(Shop shop, Mapper mapper, InventoryRepository inventoryRepo) {
        this.shop = shop;
        this.mapper = mapper;
        this.inventoryRepo = inventoryRepo;
    }

    @PutMapping("/add")
    ResponseEntity addToCart(@RequestBody ProductDto product, int amount) {
        shop.addToCart(mapper.mapToProduct(product), amount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/remove")
    ResponseEntity removeFromCart(@RequestBody ProductDto product) {
        shop.removeFromCart(mapper.mapToProduct(product));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/checkout")
    ResponseEntity checkout() {


        if(shop.getCart()==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        shop.checkout();
        var currentInventory = inventoryRepo.findAll();
        var inventoryAfterPurchase = shop.getInventory().getInventory();

        for (InventoryEntity product : currentInventory)
            checkIfExistsAndUpdateAmount(inventoryAfterPurchase, product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public static void checkIfExistsAndUpdateAmount(Map<Product, Integer> map, InventoryEntity product) {
        if (map.containsKey(product.getProduct()))
            product.setAmount(map.get(product));

    }

}
