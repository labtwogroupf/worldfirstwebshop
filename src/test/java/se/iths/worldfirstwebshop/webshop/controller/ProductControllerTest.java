package se.iths.worldfirstwebshop.webshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.service.ProductService;
import se.iths.worldfirstwebshop.webshop.service.ShopService;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductController controller;
    @MockBean
    private ProductRepository repo;
    @MockBean
    Mapper mapper;

    @MockBean
    ShopService shopService;
    @MockBean
    InventoryRepository inventoryRepo;
    @MockBean
    Inventory inventory;
    @SpyBean
    Cart cart;
    @SpyBean
    Shop shop;
    @MockBean
    ProductService productService;

    @Test
    void getAllProductsShouldReturnListOfProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());

        verify(productService, times(1)).getAllProducts();

    }

    @Test
    void getAProductShouldReturnAProduct() throws Exception {

        mockMvc.perform(get("/api/products/{id}", 1))
                .andExpect(status().isOk());

        verify(productService, times(1)).getAProduct(1L);
    }

    @Test
    void addProductShouldAddProduct() throws Exception {
        String json = """
                {
                "id":1,
                "name":"Black tea",
                "price":59,
                "isbn":"100"
                }
                """;
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(productService, times(1)).addProduct(any(ProductDto.class));

    }
}
