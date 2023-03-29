package se.iths.worldfirstwebshop.webshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.service.ShopService;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;








@WebMvcTest(controllers = ShopController.class)
class ShopControllerTest {

    @Autowired
    ShopController shopController;
    @Autowired
    MockMvc mockMvc;
    @SpyBean
    ShopService shopService;
    @MockBean
    InventoryRepository inventoryRepo;
    @MockBean
    Inventory inventory;
    @SpyBean
    Cart cart;
    @SpyBean
    Mapper mapper;
    @SpyBean
    Shop shop;


    @Test
    void addToCart() {
    }

    @Test
    void removeFromCart() {
    }

    @Test
    void checkout() {
    }
}
