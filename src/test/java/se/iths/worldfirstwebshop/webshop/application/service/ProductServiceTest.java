package se.iths.worldfirstwebshop.webshop.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.application.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.application.entity.ProductEntity;
import se.iths.worldfirstwebshop.webshop.application.repository.ProductRepository;
import se.iths.worldfirstwebshop.webshop.application.security.SecurityConfig;
import se.iths.worldfirstwebshop.webshop.application.security.UserCredentialsRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ProductService.class)
@ContextConfiguration(classes = {SecurityConfig.class, ProductService.class})
class ProductServiceTest {
    @Autowired
    ProductService productService;
    @MockBean
    ProductRepository productRepo;
    @MockBean
    Mapper mapper;
    @MockBean
    UserCredentialsRepository userCredentialsRepository;
    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    void getProductByIdReturnsAProductDto() {
        var product = new ProductEntity();
        var productDto = new ProductDto();
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        when(mapper.mapToDto(product)).thenReturn(productDto);

        var result = productService.getAProduct(1L);

        verify(productRepo, times(1)).findById(1L);
        verify(mapper, times(1)).mapToDto(product);
        assertThat(productDto).isEqualTo(result);
    }

    @Test
    void addingProductSavesToRepo() {
        var productDto = new ProductDto();
        var entity = new ProductEntity();
        when(mapper.mapToEntity(productDto)).thenReturn(entity);

        productService.addProduct(productDto);

        verify(productRepo, times(1)).save(entity);

    }

    @Test
    void getAllProductsReturnsListOfDto() {
        var product = new ProductEntity();
        var productDto = new ProductDto();
        when(productRepo.findAll()).thenReturn(List.of(product));
        when(mapper.mapToDto(product)).thenReturn(productDto);

        var result = productService.getAllProducts();

        verify(productRepo, times(1)).findAll();
        verify(mapper, times(1)).mapToDto(product);
        assertThat(result).contains(productDto);
    }
}
