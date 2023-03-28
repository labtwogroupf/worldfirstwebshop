package se.iths.worldfirstwebshop.webshop.controller;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import se.iths.worldfirstwebshop.webshop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.mockito.Mockito.when;
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
    @Test
    void addProductShouldAddProduct() throws Exception{
        ProductEntity product1 = new ProductEntity();
        product1.setId(1L);
        product1.setName("Black tea");
        product1.setPrice(BigDecimal.valueOf(100));
        product1.setIsbn("1234567");

        when(repo.save(product1)).thenReturn(product1);
        when(repo.findById(1L)).thenReturn(Optional.of(product1));
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                """
                {
                "id":1,
                "name":"Black Tea",
                "price":100,
                "isbn":"1234567"
                }
                """))
                .andExpect(status().isCreated());





    }
}
