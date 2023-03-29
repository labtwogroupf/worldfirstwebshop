package se.iths.worldfirstwebshop.webshop.service;

import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

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

        var inventoryInMemory = shop.getInventory().getInventory();

        inventoryRepo.deleteAll();

        var keys = inventoryInMemory.keySet().stream().toList();

        for (int i = 0; i < keys.size(); i++) {
            var entity = new InventoryEntity();
            entity.setProduct(mapper.mapToEntity(keys.get(i)));
            entity.setAmount(inventoryInMemory.get(keys.get(i)));
            inventoryRepo.save(entity);
        }

    }

}
