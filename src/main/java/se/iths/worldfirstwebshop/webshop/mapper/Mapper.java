package se.iths.worldfirstwebshop.webshop.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public Mapper() {

    }

    public ProductEntity mapToEntity(Product product) {
        var productE = new ProductEntity();
        productE.setName(product.getName());
        productE.setIsbn(product.getIsbn());
        productE.setPrice(product.getPrice());
        productE.setId(product.getId());
        return productE;
    }

    public ProductEntity mapToEntity(ProductDto product) {
        var productE = new ProductEntity();
        productE.setName(product.getName());
        productE.setIsbn(product.getIsbn());
        productE.setPrice(product.getPrice());
        productE.setId(product.getId());
        return productE;
    }

    public Product mapToProduct(ProductEntity product) {
        return new Product(product.getName(), product.getPrice(), product.getIsbn(), product.getId());
    }

    public Product mapToProduct(ProductDto product) {
        return new Product(product.getName(), product.getPrice(), product.getIsbn());
    }

    public ProductDto mapToDto(ProductEntity product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getIsbn(), product.getId());
    }

    public ProductDto mapToDto(Product product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getIsbn(), product.getId());
    }

    public InventoryDto mapToInventoryDto(InventoryEntity inv) {
        return new InventoryDto(inv.getAmount(), mapToDto(inv.getProduct()));
    }

    public List<InventoryEntity> getInventoryEntitiesAsLists(Map<ProductEntity, Integer> map) {
        return map.entrySet()
                .stream()
                .map(Mapper::mapToInventoryEntity)
                .toList();
    }

    private static InventoryEntity mapToInventoryEntity(Map.Entry<ProductEntity, Integer> entrySet) {
        InventoryEntity entry = new InventoryEntity();
        entry.setProduct(entrySet.getKey());
        entry.setAmount(entrySet.getValue());
        return entry;
    }

    public Map<Product, Integer> mapToInventory(List<InventoryEntity> entities) {
        return entities.stream()
                .collect(Collectors.toMap(mapToProduct(), InventoryEntity::getAmount));
    }

    @NotNull
    private Function<InventoryEntity, Product> mapToProduct() {
        return inventoryEntity -> mapToProduct(inventoryEntity.getProduct());
    }

}
