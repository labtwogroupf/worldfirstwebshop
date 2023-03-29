package se.iths.worldfirstwebshop.webshop.mapper;

import org.springframework.stereotype.Component;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
