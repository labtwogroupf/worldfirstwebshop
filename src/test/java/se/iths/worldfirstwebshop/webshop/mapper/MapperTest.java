package se.iths.worldfirstwebshop.webshop.mapper;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.domain.Product;
import se.iths.worldfirstwebshop.webshop.entities.ProductEntity;
import se.iths.worldfirstwebshop.webshop.entities.InventoryEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MapperTest {

    Mapper mapper = new Mapper();
    Product product = getProduct();
    ProductEntity productEntity = getEntity();
    ProductDto productDto = getDto();

    @Test
    void mappingProductReturnsCorrectProductEntity() {
        assertThat(productEntity).usingRecursiveComparison().isEqualTo(mapper.mapToEntity(product));
    }

    @Test
    void mappingDtoReturnCorrectEntity() {
        assertThat(productEntity).usingRecursiveComparison().isEqualTo(mapper.mapToEntity(productDto));
    }

    @Test
    void mappingProductReturnsCorrectProductDto() {
        assertThat(productDto).usingRecursiveComparison().isEqualTo(mapper.mapToDto(product));
    }

    @Test
    void mappingDtoReturnCorrectProduct() {
        assertThat(product).usingRecursiveComparison().isEqualTo(mapper.mapToProduct(productDto));
    }

    @Test
    void mappingEntityReturnsCorrectProduct() {
        assertThat(product).usingRecursiveComparison().isEqualTo(mapper.mapToProduct(productEntity));
    }

    @Test
    void mappingInventoryEntityReturnsCorrectDto() {
        var inventoryEntity = new InventoryEntity();
        inventoryEntity.setProduct(productEntity);
        inventoryEntity.setAmount(10);
        var inventoryDto = new InventoryDto(10, mapper.mapToDto(productEntity));

        assertThat(inventoryDto).usingRecursiveComparison().isEqualTo(mapper.mapToInventoryDto(inventoryEntity));

    }

    private ProductEntity getEntity() {
        var productEntity = new ProductEntity();
        productEntity.setName("Grönt te");
        productEntity.setPrice(BigDecimal.TEN);
        productEntity.setIsbn("1234");
        return productEntity;
    }

    private Product getProduct() {
        return new Product("Grönt te", BigDecimal.TEN, "1234");
    }

    private ProductDto getDto() {
        return new ProductDto("Grönt te", BigDecimal.TEN, "1234");
    }

}
