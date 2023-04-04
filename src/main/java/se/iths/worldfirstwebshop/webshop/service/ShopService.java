package se.iths.worldfirstwebshop.webshop.service;


import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class ShopService {

    private final Shop shop;
    private final Mapper mapper;
    private final InventoryRepository inventoryRepo;


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
        var updatedInventory = shop.getInventory()
                .getInventory()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(map -> mapper.mapToEntity(map.getKey()), Map.Entry::getValue));

        inventoryRepo.deleteAll();
        inventoryRepo.saveAll(mapper.getInventoryEntitiesAsLists(updatedInventory));

    }

}

//package se.iths.worldfirstwebshop.webshop.service;
//
//
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//        import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.stereotype.Service;
//        import se.iths.worldfirstwebshop.webshop.access.Shop;
//        import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
//        import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
//        import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
//        import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
//
//        import java.util.Map;
//        import java.util.stream.Collectors;
//
//@Service
//public class ShopService {
//
//    Shop shop;
//    Mapper mapper;
//    InventoryRepository inventoryRepo;
//
//    public ShopService(Mapper mapper, InventoryRepository inventoryRepo) {
//        this.shop = new Shop(mapper.mapToInventory(inventoryRepo.findAll()));
//        this.mapper = mapper;
//        this.inventoryRepo = inventoryRepo;
//
//    }
//
//    public void addToCart(ProductDto product, int amount) {
//        shop.addToCart(shop.getInventory().getProductInInventory(mapper.mapToProduct(product)), amount);
//    }
//
//    public void removeFromCart(ProductDto product) {
//        shop.removeFromCart(shop.getInventory().getProductInInventory(mapper.mapToProduct(product)));
//    }
//
//    public void checkout() {
//        shop.checkout();
//        updateInventoryDatabase();
//    }
//
//    private void updateInventoryDatabase() {
//        var updatedInventory = shop.getInventory()
//                .getInventory()
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(map -> mapper.mapToEntity(map.getKey()), Map.Entry::getValue));
//
//        inventoryRepo.deleteAll();
//        inventoryRepo.saveAll(mapper.getInventoryEntitiesAsLists(updatedInventory));
//
//    }
//}
