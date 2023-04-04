package se.iths.worldfirstwebshop.webshop.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.messageQueue.Publisher;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.security.SecurityConfig;
import se.iths.worldfirstwebshop.webshop.service.ShopService;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(authorities = "ADMIN")
@WebMvcTest(ShopController.class)
@ContextConfiguration(classes = {SecurityConfig.class, ShopController.class})
class ShopControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
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
    @MockBean
    Publisher publisher;


    @Test
    void shouldReturnStatusCreatedWhenAddingToCart() throws Exception {
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

        verify(publisher).addToQueue(any(ProductDto.class), any(Integer.class));
    }

    @Test
    void shouldReturnStatusCreatedWhenRemovingFromCart() throws Exception {
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

        verify(shopService).removeFromCart(any(ProductDto.class));
    }

    @Test
    void shouldCheckoutAndReturnStatusOk() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/shop/checkout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(shopService, Mockito.times(1)).checkout();
    }
}
