package se.iths.worldfirstwebshop.webshop.service;

import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.stream.Collectors;

@Service
public class ShopService {

    Shop shop;
    Mapper mapper;
    InventoryRepository inventoryRepo;

    public ShopService(Mapper mapper, InventoryRepository inventoryRepo) {
        this.shop = new Shop(mapper.mapToInventory(inventoryRepo.findAll()));
        this.mapper = mapper;
        this.inventoryRepo = inventoryRepo;

    }

    public void addToCart(ProductDto product, int amount) {
        shop.addToCart(shop.getInventory().getProductInInventory(mapper.mapToProduct(product)), amount);
    }

    public void removeFromCart(ProductDto product) {
        shop.removeFromCart(shop.getInventory().getProductInInventory(mapper.mapToProduct(product)));
    }

    public void checkout() {
        shop.checkout();
        updateInventoryDatabase();
    }

    private void updateInventoryDatabase() {
        var inventoryInMemory = shop.getInventory().getInventory();

        inventoryRepo.deleteAll();

        var keys = inventoryInMemory.keySet().stream().toList();

        for (var key : keys) {
            var entity = new InventoryEntity();
            entity.setProduct(mapper.mapToEntity(key));
            entity.setAmount(inventoryInMemory.get(key));
            inventoryRepo.save(entity);
        }

    }
    private void updateInventoryDatabase(String s) {
        var inventoryInMemory = shop.getInventory().getInventory();

        inventoryRepo.deleteAll();

        //var keys = inventoryInMemory.keySet().stream().toList();

              // var l = inventoryInMemory.entrySet().stream().map(entry -> mapper.mapToEntity(entry.getKey()));
        //inventoryInMemory.keySet().stream().map(product -> mapper.mapToEntity(product)).collect(Collectors.toMap(productEntity -> productEntity));
        var result = inventoryInMemory
                .entrySet()
                .stream()
                .collect(Collectors.toMap(map -> mapper.mapToEntity(map.getKey()), map -> map.getValue()));

        //var result2 = result.entrySet().stream().toList();

        inventoryRepo.saveAll(mapper.getInventoryEntitiesAsList(result));
//
//        for (var key : keys) {
//            var entity = new InventoryEntity();
//            entity.setProduct(mapper.mapToEntity(key));
//            entity.setAmount(inventoryInMemory.get(key));
//            inventoryRepo.save(entity);
//        }

    }

}
