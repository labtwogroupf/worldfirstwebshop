package se.iths.worldfirstwebshop.webshop.service;

import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.Map;

@Service
public class ShopService {

    Shop shop;
    Mapper mapper;
    InventoryRepository inventoryRepo;

    public ShopService(Mapper mapper, InventoryRepository inventoryRepo) {
        this.shop = new Shop();
        this.mapper = mapper;
        this.inventoryRepo = inventoryRepo;
    }

    public void addToCart(ProductDto product, int amount) {
        shop.addToCart(mapper.mapToProduct(product), amount);
    }

    public void removeFromCart(ProductDto product) {
        shop.removeFromCart(mapper.mapToProduct(product));
    }

    public void checkout() {
        shop.checkout();
        var currentInventory = inventoryRepo.findAll();
        var inventoryAfterPurchase = shop.getInventory().getInventory();

        for (InventoryEntity product : currentInventory)
            checkIfExistsAndUpdateAmount(inventoryAfterPurchase, product);

    }

    private static void checkIfExistsAndUpdateAmount(Map<Product, Integer> map, InventoryEntity product) {
        if (map.containsKey(product.getProduct()))
            product.setAmount(map.get(product));

    }

}
