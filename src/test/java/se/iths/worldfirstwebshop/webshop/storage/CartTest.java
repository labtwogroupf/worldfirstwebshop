package se.iths.worldfirstwebshop.webshop.storage;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart products = new Cart();

    @Test
    void totalPriceShouldReturn100() {
        products.add(new Product("Svartvinbär", BigDecimal.valueOf(20), 2));
        products.add(new Product("Citron", BigDecimal.valueOf(30), 1));
        products.add(new Product("Vanilj", BigDecimal.valueOf(10), 3));
        BigDecimal expected = BigDecimal.valueOf(100);

        assertEquals(expected, products.totalPrice());

    }

    @Test
    void should() {
        products.add(new Product("Svartvinbär", BigDecimal.valueOf(20), 2));
        products.clear();
        products.add(new Product("Citron", BigDecimal.valueOf(30), 1));
        products.add(new Product("Vanilj", BigDecimal.valueOf(10), 3));
        int expected = 2;

        assertEquals(expected, products.getSize());



    }



}
