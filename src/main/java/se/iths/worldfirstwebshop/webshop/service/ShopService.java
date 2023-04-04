package se.iths.worldfirstwebshop.webshop.service;
import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.shop.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;

import java.util.List;
import java.util.Map;
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

    public Map<Product,Integer> getCart() {
        return shop.getCart().getProducts();
    }

    public void checkout() {
        shop.checkout();
        updateInventoryDatabase();
    }
    public List<Map.Entry<Product, Integer>> getCart(){
        return this.shop.getCart().getProducts().entrySet().stream().toList();
    }


    private void updateInventoryDatabase() {
        var updatedInventory = shop.getInventory()
                .getInventory()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(map -> mapper.mapToEntity(map.getKey()), Map.Entry::getValue));

        inventoryRepo.deleteAll();
        inventoryRepo.saveAll(mapper.getInventoryEntitiesAsLists(updatedInventory));
    }


}
