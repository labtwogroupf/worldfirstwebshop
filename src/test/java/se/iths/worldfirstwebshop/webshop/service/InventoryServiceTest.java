package se.iths.worldfirstwebshop.webshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.util.List;

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
    void getProductReturnsListOfDtos(){
        var inventoryEntity = new InventoryEntity();
        var inventoryDto = new InventoryDto();
        when(inventoryRepo.findAll()).thenReturn(List.of(inventoryEntity));
        when(mapper.mapToInventoryDto(inventoryEntity)).thenReturn(inventoryDto);

        var result = inventoryService.getProducts();

        verify(inventoryRepo, times(1)).findAll();
        verify(mapper, times(1)).mapToInventoryDto(inventoryEntity);
        assertThat(result).contains(inventoryDto);
    }

}
