package se.iths.worldfirstwebshop.webshop.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.entities.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.entities.InventoryEntity;

import java.util.List;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InventoryController inventoryController;
    @MockBean
    private InventoryRepository inventoryRepo;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private Mapper mapper;
    @MockBean
    private ShopController shopController;

    @Test
    void buying5ItemsShouldHave2LeftInStockIfItStartedWith7() throws Exception {
        long id = 1L;
        int amount = 5;
        InventoryEntity inventory = new InventoryEntity();
        inventory.setAmount(7);
        when(inventoryRepo.findById(id)).thenReturn(of(inventory));
        when(inventoryRepo.save(inventory)).thenReturn(inventory);

        mockMvc.perform(put("/api/inventory/subtract/{id}", 1)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());
        assertThat(inventory.getAmount()).isEqualTo(2);

    }

    @Test
    void adding5ToStockWhenWeHave3ShouldReturnAmountEqualTo8() throws Exception {
        long id = 1L;
        int amount = 5;
        InventoryEntity inventory = new InventoryEntity();
        inventory.setAmount(3);
        when(inventoryRepo.findById(id)).thenReturn(of(inventory));
        when(inventoryRepo.save(inventory)).thenReturn(inventory);

        mockMvc.perform(put("/api/inventory/add/{id}", 1)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());
        assertThat(inventory.getAmount()).isEqualTo(8);

    }

    @Test
    void addingAProductViaHttpRequestShouldAddItToInventoryRepository() throws Exception {
        var product = new ProductEntity();
        product.setName("Grönt Te");
        var inventory = new InventoryEntity();
        inventory.setProduct(product);
        when(productRepository.findById(1L)).thenReturn(of(product));
        when(inventoryRepo.findById(1L)).thenReturn(of(inventory));

        mockMvc.perform(post("/api/inventory")
                        .param("id", String.valueOf(1L))
                        .param("amount", String.valueOf(5)))
                .andExpect(status().isOk());
        assertThat(inventoryRepo.findById(1L).get().getProduct()).isEqualTo(product);

    }

    @Test
    void deleteAnItemFromInventoryShouldRemoveIt() throws Exception {
        var id = 1L;
        var inventory = new InventoryEntity();
        inventory.setProduct(new ProductEntity());
        inventory.setId(id);
        when(inventoryRepo.findById(id)).thenReturn(of(inventory));

        mockMvc.perform(delete("/api/inventory/delete/{id}", id))
                .andExpect(status().isOk());
        Mockito.verify(inventoryRepo, times(1)).deleteById(id);

    }

    @Test
    void returnAllProductShouldReturnSize1IfWeHave1ProductsInInventory() throws Exception {
        var inventory = new InventoryEntity();
        var listOfInventory = List.of(inventory);
        when(inventoryRepo.findAll()).thenReturn(listOfInventory);
        when(mapper.mapToInventoryDto(inventory)).thenReturn(new InventoryDto());

        mockMvc.perform(get("/api/inventory/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

}
