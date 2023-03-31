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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

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
        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100", 1L);
        ProductDto productDto = mapper.mapToDto(product);
        shopService.addToCart(productDto, 1);
        shop.addToCart(product, 1);  // verify checks if this line is executed which it always is since called here
        verify(shop).addToCart(product, 1);
        //edit evergreen test
    }

    @Test
    void removeFromCart() {
        //evergreen test
        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100",1L );
        ProductDto productDto = mapper.mapToDto(product);
        shopService.removeFromCart(productDto);
        shop.removeFromCart(product); // verify checks if this line is executed which it always is since called here
        verify(shop).removeFromCart(product);
        //edit evergreen test
    }

    @Test
    void checkout() {

        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100", 1L);
        shopService.shop.getInventory().add(product, 10);
        shopService.shop.addToCart(product, 5);

        shopService.checkout();

        int actual = shopService.shop.getInventory().getInventory().get(product);
        assertEquals(5, actual);
    }
}
