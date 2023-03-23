package se.iths.worldfirstwebshop.webshop.mapper;

import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;

public class Mapper {

    public ProductEntity map(Product product) {
        var productE = new ProductEntity();
        productE.setName(product.getName());
        productE.setIsbn(product.getIsbn());
        productE.setPrice(product.getPrice());
        return productE;
    }

    public ProductEntity map(ProductDto product) {
        var productE = new ProductEntity();
        productE.setName(product.getName());
        productE.setIsbn(product.getIsbn());
        productE.setPrice(product.getPrice());
        return productE;
    }

    public Product mapToProduct(ProductEntity product) {
        return new Product(product.getName(), product.getPrice(), product.getIsbn());
    }

    public Product mapFromDto(ProductDto product) {
        return null;
    }

    public ProductDto map(ProductEntity product) {
        return null;
    }

    public ProductDto mapFromProductToDto(Product product) {
        return null;
    }

}
