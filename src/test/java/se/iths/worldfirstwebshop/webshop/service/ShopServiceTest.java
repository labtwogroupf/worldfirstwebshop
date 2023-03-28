package se.iths.worldfirstwebshop.webshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.controller.InventoryController;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ShopService.class)
class ShopServiceTest {


@Autowired
private ShopService shopService;
@Autowired
private MockMvc mockMvc;
@MockBean
InventoryRepository inventoryRepo;
@SpyBean
Mapper mapper;
@SpyBean
Shop shop;
@MockBean
Inventory inventory;
@SpyBean
Cart cart;
@MockBean
InventoryController inventoryController;

    @Test
    void addToCart() {
        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100",1L );
        ProductDto productDto = mapper.mapToDto(product);
        shopService.addToCart(productDto, 1);
        shop.addToCart(product,1);
        verify(shop).addToCart(product,1);
    }

    @Test
    void removeFromCart() {
        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100",1L );
        ProductDto productDto = mapper.mapToDto(product);
        shopService.addToCart(productDto, 1);
        shop.removeFromCart(product);
        verify(shop).removeFromCart(product);
    }

    //TODO: Comment in when checkout is implemented properly
//    @Test
//    void checkout() {
//
//        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100", 1L);
//        List<InventoryEntity> currentInventory = List.of(
//                new InventoryEntity(product, 3));
//
//        when(inventoryRepo.findAll()).thenReturn(currentInventory);
//
//        shop.getInventory().add(product, 3);
//        shop.addToCart(product, 2);
//
//        shopService.checkout();
//
//        int actual = shop.getInventory().getInventory().get(product);
//
//        assertEquals(1,actual);
//    }
}
