package se.iths.worldfirstwebshop.webshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(controllers = InventoryService.class)
class InventoryServiceTest {

    @Autowired
    InventoryService inventoryService;
    @MockBean
    InventoryRepository inventoryRepo;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    Mapper mapper;

    @Test
    void getProductReturnsListOfDtos() {

        var inventoryEntity = new InventoryEntity(new Product("Te", BigDecimal.ONE, "123"), 10);
        var inventoryDto = new InventoryDto();
        when(inventoryRepo.findAll()).thenReturn(List.of(inventoryEntity));
        when(mapper.mapToInventoryDto(inventoryEntity)).thenReturn(inventoryDto);

        var result = inventoryService.getProducts();

        verify(inventoryRepo, times(1)).findAll();
        verify(mapper, times(1)).mapToInventoryDto(inventoryEntity);
        assertThat(result).contains(inventoryDto);
    }

    @Test
    void removeMethodEmptiesInventoryRepo() {

        inventoryService.removeAll();

        verify(inventoryRepo, times(1)).deleteAll();

    }
    @Test
    void removeByIdRemovesFromDatabase(){

        when(inventoryRepo.findById(1L)).thenReturn(Optional.of(new InventoryEntity()));
        inventoryService.removeById(1L);

        verify(inventoryRepo, times(1)).findById(1L);
        verify(inventoryRepo, times(1)). deleteById(1L);
    }
    
    @Test
    void updateWhenSoldMethodsUpdateDatabase(){

            when(inventoryRepo.findById(1L)).thenReturn(Optional.of(new InventoryEntity()));

            inventoryService.updateAddToStock(1L, 5);

            verify(inventoryRepo).findById(1L);

            verify(inventoryRepo).save((any(InventoryEntity.class)));

        }

    @Test
    void updateAddToStockMethodsUpdateDatabase(){

            when(inventoryRepo.findById(1L)).thenReturn(Optional.of(new InventoryEntity()));

            inventoryService.updateWhenSold(1L, 5);

            verify(inventoryRepo).findById(1L);

            verify(inventoryRepo).save((any(InventoryEntity.class)));

        }

        @Test
        void addProductSavesToDatabase(){

            when(productRepository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));

            inventoryService.addProduct(1L, 10);
            verify(inventoryRepo, times(1)).save(any(InventoryEntity.class));
        }


}
