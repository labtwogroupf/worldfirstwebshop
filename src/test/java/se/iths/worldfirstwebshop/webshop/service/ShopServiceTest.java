package se.iths.worldfirstwebshop.webshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.worldfirstwebshop.webshop.access.Shop;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.repository.InventoryRepository;
import se.iths.worldfirstwebshop.webshop.storage.InventoryEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @Mock
    private Shop shop;
    @Mock
    private Mapper mapper;
    @Mock
    private InventoryRepository inventoryRepo;
    @InjectMocks
    private ShopService shopService;


    @Test
    void addToCart() {
        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100",1L );
        ProductDto productDto = mapper.mapToDto(product);
        shopService.addToCart(productDto, 1);
        shop.addToCart(product,1);
        verify(shop).addToCart(product,1);
    }

    @Test
    void removeFromCart() {
        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100",1L );
        ProductDto productDto = mapper.mapToDto(product);
        shopService.addToCart(productDto, 1);
        shop.removeFromCart(product);
        verify(shop).removeFromCart(product);
    }

    @Test
    void checkout() {

        Product product = new Product("Black tea", BigDecimal.valueOf(59), "100", 1L);
        List<InventoryEntity> currentInventory = List.of(
                new InventoryEntity(product, 3));
        when(inventoryRepo.findAll()).thenReturn(currentInventory);

        shop.getInventory().add(product, 3);
        shop.addToCart(product, 2);

        //shopService.checkout();

        int actual = shop.getInventory().getInventory().get(product);

        assertEquals(1,actual);
    }
}
