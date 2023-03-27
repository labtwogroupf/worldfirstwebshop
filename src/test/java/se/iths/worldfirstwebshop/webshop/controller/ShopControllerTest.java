package se.iths.worldfirstwebshop.webshop.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ShopController.class)
class ShopControllerTest {



    @Autowired
    private ShopController shopController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InventoryRepository inventoryRepo;
    @MockBean
    private Mapper mapper;
    @MockBean
    private Shop shop;
    @MockBean
    Inventory inventory;
    @MockBean
    Cart cart;
    @MockBean
    InventoryController inventoryController;


    @Test
    void testAddToCart() throws Exception {
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
                        .param("amount", "2"))
                .andExpect(status().isCreated());
    }

    @Test
    void removeFromCart() throws Exception {
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
    void checkout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/checkout"))
                .andExpect(status().isNotFound());

    }


//    @Test
//    void checkout2() throws Exception {
//
//        ProductDto productDto = new ProductDto("Black tea", BigDecimal.valueOf(59), "100", 1L);
//        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100", 1L);
////        InventoryEntity iE = new InventoryEntity();
////        var listOfInventory = List.of(inventoryEntity);
//        List<InventoryEntity> currentInventory = List.of(new InventoryEntity(product, 2));
//        //currentInventory.add(inventoryEntity);
//
//
//        inventory.add(product, 2);
//        shop.addToCart(product, 2);
//        cart.add(product,2);
//        when(mapper.mapToDto(product)).thenReturn(productDto);
//        when(mapper.mapToProduct(productDto)).thenReturn(product);
//        when(shop.getCart()).thenReturn(cart);
//        when(inventoryRepo.findAll()).thenReturn(currentInventory);
//
//       // shopController.addToCart(productDto, 2);
//
////        when(inventoryRepo.findAll()).thenReturn(List.of());
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/shop/checkout"))
//                .andExpect(status().isOk());
//
//
//
//    }
}
