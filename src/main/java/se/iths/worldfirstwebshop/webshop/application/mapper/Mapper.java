package se.iths.worldfirstwebshop.webshop.application.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import se.iths.worldfirstwebshop.webshop.application.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.domain.product.Product;
import se.iths.worldfirstwebshop.webshop.application.entity.ProductEntity;
import se.iths.worldfirstwebshop.webshop.application.entity.InventoryEntity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mapper {



    public ProductEntity mapToEntity(Product product) {
        var productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setIsbn(product.getIsbn());
        productEntity.setPrice(product.getPrice());
        productEntity.setId(product.getId());
        return productEntity;
    }

    public ProductEntity mapToEntity(ProductDto product) {
        var productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setIsbn(product.getIsbn());
        productEntity.setPrice(product.getPrice());
        productEntity.setId(product.getId());
        return productEntity;
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



    public class ProductCartDto {
        private Long id;
        private String name;
        // Add other fields if necessary

        public ProductCartDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }




}
