package se.iths.worldfirstwebshop.webshop.controller;


import org.hamcrest.Matchers;
import org.hibernate.engine.jdbc.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)

class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository repo;
    @MockBean
    private ProductController controller;
    @MockBean
    private ProductDto productDto;
    @MockBean
    private ProductEntity productEntity;



    @Test
    void getAllProductsShouldReturnAmount() throws Exception{
        ProductEntity product1 = new ProductEntity();
        product1.setName("Black tea");
        ProductEntity product2 = new ProductEntity();
        product2.setName("Green tea");

        when(repo.findAll()).thenReturn(List.of(product1,product2));
        var result = repo.findAll();
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());

        assertEquals(2,result.size());
    }

    @Test
    void getAProductShouldReturnAProduct() throws Exception{
        ProductEntity product1 = new ProductEntity();
        product1.setName("Black tea");
        ProductEntity product2 = new ProductEntity();
        product2.setName("Green tea");

        when(repo.findById(2L)).thenReturn(Optional.of(product2));
        var result = repo.findById(2L).get();

        mockMvc.perform(get("/api/products/2"))
                .andExpect(status().isOk());
        assertEquals(product2,result);
    }
}
