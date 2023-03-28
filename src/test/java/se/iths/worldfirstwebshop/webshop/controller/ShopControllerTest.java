package se.iths.worldfirstwebshop.webshop.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShopController.class)
class ShopControllerTest {

    @Autowired
    private ShopController shopController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InventoryRepository inventoryRepo;
    @SpyBean
    private Mapper mapper;
    @SpyBean
    private Shop shop;
    @MockBean
    Inventory inventory;
    @SpyBean
    Cart cart;
    @MockBean
    InventoryController inventoryController;

    @Test
    void addToCartShouldReturnCorrectResponseCode() throws Exception {

        String json = """
            {
            "id":1,
            "name":"Black tea",
            "price":59,
            "isbn":"100"
            }
            """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/shop/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .param("amount", "1"))
                .andExpect(status().isCreated());
    }

    @Test
    void removeFromCartShouldReturnCorrectResponseCode() throws Exception {
        String json = """
                {
                "id":1,
                "name":"Black tea",
                "price":59,
                "isbn":"100"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/shop/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .param("amount", "1"))
                .andExpect(status().isCreated());
    }


    @Test
    void checkoutShouldRemoveCorrectAmountOfProductsFromInventory() throws Exception {

        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100", 1L);
        List<InventoryEntity> currentInventory = List.of(
                new InventoryEntity(product, 3));
        when(inventoryRepo.findAll()).thenReturn(currentInventory);

        shop.getInventory().add(product, 3);
        shop.addToCart(product, 2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/checkout"))
                .andExpect(status().isOk());

        int actual = shop.getInventory().getInventory().get(product);

        assertEquals(1,actual);

    }
}
