package se.iths.worldfirstwebshop.webshop.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class InventoryService {
    InventoryRepository inventoryRepo;
    ProductRepository productRepo;
    Mapper mapper;

    public InventoryService(InventoryRepository inventoryRepo, ProductRepository productRepo, Mapper mapper) {
        this.inventoryRepo = inventoryRepo;
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    public List<InventoryDto> getProducts() {
        return inventoryRepo.findAll()
                .stream()
                .map(mapToDto())
                .toList();
    }

    public void removeAll() {
        inventoryRepo.deleteAll();
    }

    public void removeById(Long id) {
        inventoryRepo.findById(id).ifPresent(i -> inventoryRepo.deleteById(id));
    }

    public void updateWhenSold(Long id, int amount) {
        inventoryRepo.findById(id)
                .ifPresent(updateAmount(amount, Operation.SUBTRACT)
                .andThen(saveToDatabase()));
    }

    public void updateAddToStock(Long id, int amount) {
        inventoryRepo.findById(id)
                .ifPresent(updateAmount(amount, Operation.ADD)
                .andThen(saveToDatabase()));

    }

    @NotNull
    private Consumer<InventoryEntity> saveToDatabase() {
        return inventory -> inventoryRepo.save(inventory);
    }

    @NotNull
    private static Consumer<InventoryEntity> updateAmount(int amount, Operation operation) {
        return switch (operation) {
            case ADD -> inventoryEntity -> inventoryEntity.setAmount(inventoryEntity.getAmount() + amount);
            case SUBTRACT -> inventoryEntity -> inventoryEntity.setAmount(inventoryEntity.getAmount() - amount);
        };
    }

    public void addProduct(Long productId, int amount) {
        var product = productRepo.findById(productId);
        product.ifPresent(createAndSaveEntity(amount, product.get()));
    }

    @NotNull
    private Consumer<ProductEntity> createAndSaveEntity(int amount, ProductEntity product) {
        return productEntity -> {
            var inventoryEntity = new InventoryEntity();
            inventoryEntity.setProduct(product);
            inventoryEntity.setAmount(amount);
            inventoryRepo.save(inventoryEntity);
        };
    }

    @NotNull
    private Function<InventoryEntity, InventoryDto> mapToDto() {
        return inventoryEntity -> mapper.mapToInventoryDto(inventoryEntity);
    }
}

enum Operation {
    ADD, SUBTRACT
}
