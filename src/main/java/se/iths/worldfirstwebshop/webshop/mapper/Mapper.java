package se.iths.worldfirstwebshop.webshop.mapper;

import org.springframework.stereotype.Component;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.domain.Product;
import se.iths.worldfirstwebshop.webshop.entities.ProductEntity;
import se.iths.worldfirstwebshop.webshop.entities.InventoryEntity;

@Component
public class Mapper {

    public Mapper(){

    }
    public InventoryDto mapToInventoryDto(InventoryEntity inv) {

        return new InventoryDto(inv.getAmount(),mapToDto(inv.getProduct()));
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
        return new Product(product.getName(), product.getPrice(), product.getIsbn(),product.getId());
    }

    public Product mapToProduct(ProductDto product) {
        return new Product(product.getName(), product.getPrice(), product.getIsbn(), product.getId());
    }

    public ProductDto mapToDto(ProductEntity product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getIsbn(), product.getId());
    }

    public ProductDto mapToDto(Product product) {
        return new ProductDto(product.getName(), product.getPrice(), product.getIsbn(), product.getId());
    }

}
