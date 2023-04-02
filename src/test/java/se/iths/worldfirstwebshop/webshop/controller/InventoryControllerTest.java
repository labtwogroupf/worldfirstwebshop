package se.iths.worldfirstwebshop.webshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.security.SecurityConfig;
import se.iths.worldfirstwebshop.webshop.service.InventoryService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
@WithMockUser(authorities = "ADMIN")
@ContextConfiguration(classes = {SecurityConfig.class, InventoryController.class})
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InventoryController inventoryController;
    @Autowired
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private InventoryService inventoryService;
    @MockBean
    private ProductController productController;
    @MockBean
    private ShopController shopController;
    @MockBean
    InventoryRepository inventoryRepository;


    @Test
    void removingFromStockShouldReturn200ok() throws Exception {
        mockMvc.perform(put("/api/inventory/subtract/{id}", 1L)//.with(user("user"))
                        .param("amount", String.valueOf(10)))
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).updateWhenSold(1L, 10);

    }

    @Test
    void addingToStockShouldReturn200ok() throws Exception {
        mockMvc.perform(put("/api/inventory/add/{id}", 1L)
                        .param("amount", String.valueOf(10)))
                        .andExpect(status().isOk());

        verify(inventoryService, times(1)).updateAddToStock(1L, 10);
    }

    @Test
    void addingAProductShouldReturn201Created() throws Exception {
        mockMvc.perform(post("/api/inventory")
                        .param("id", String.valueOf(1L))
                        .param("amount", String.valueOf(10)))
                        .andExpect(status().isCreated());

        verify(inventoryService, times(1)).addProduct(1L, 10);

    }

    @Test
    void deleteFromInventoryShouldRemove200ok() throws Exception {

        mockMvc.perform(delete("/api/inventory/delete"))
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).removeAll();
    }

    @Test
    void deleteAnItemFromInventoryShouldRemoveIt() throws Exception {

        mockMvc.perform(delete("/api/inventory/delete/{id}", 1L))
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).removeById(1L);
    }

    @Test
    void returnAllProductShouldReturn200Ok() throws Exception {

        mockMvc.perform(get("/api/inventory/products"))
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).getProducts();
    }

}
