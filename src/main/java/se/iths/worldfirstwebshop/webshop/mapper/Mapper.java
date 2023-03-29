package se.iths.worldfirstwebshop.webshop.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public Mapper() {

    }

    public InventoryDto mapToInventoryDto(InventoryEntity inv) {
        return new InventoryDto(inv.getAmount(), mapToDto(inv.getProduct()));
    }

    public Map<Product, Integer> mapToInventory(List<InventoryEntity> entities) {
        Map<Product, Integer> list = new HashMap<>();

        for (int i = 0; i < entities.size(); i++)
            list.put(mapToProduct(entities.get(i).getProduct()), entities.get(i).getAmount());

        return list;
    }

    public List<InventoryEntity> getInventoryEntitiesAsList(Map<ProductEntity, Integer> map) {
        List<InventoryEntity> list = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {
            InventoryEntity e = new InventoryEntity();
            e.setAmount(list.get(i).getAmount());
            e.setProduct(list.get(i).getProduct());
            list.add(e);
        }
//
//                map
//                .keySet()
//                .stream()
//                .map(mapToEntity())
//                .collect(Collectors.toList());

        return list;
    }

//    @NotNull
//    private static Function<ProductEntity, Object> mapToEntity() {
//        return aLong -> {
//            InventoryEntity ent = new InventoryEntity();
//            ent.setProduct(aLong);
//            ent.setAmount(ent.getAmount());
//
//        };
//    }

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

}
