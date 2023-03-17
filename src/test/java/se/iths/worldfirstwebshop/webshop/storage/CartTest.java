package se.iths.worldfirstwebshop.webshop.storage;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    List<Product> products = new ArrayList<>();
    Cart cart = new Cart(products);

    @Test
    void totalPriceShouldReturn100() {
        cart.add(new Product("Svartvinbär", BigDecimal.valueOf(20), 2));
        cart.add(new Product("Citron", BigDecimal.valueOf(30), 1));
        cart.add(new Product("Vanilj", BigDecimal.valueOf(10), 3));
        BigDecimal expected = BigDecimal.valueOf(100);

        assertEquals(expected, cart.totalPrice());

    }

    @Test
    void should() {
        cart.add(new Product("Svartvinbär", BigDecimal.valueOf(20), 2));
        cart.clear();
        cart.add(new Product("Citron", BigDecimal.valueOf(30), 1));
        cart.add(new Product("Vanilj", BigDecimal.valueOf(10), 3));
        int expected = 2;

        assertEquals(expected, cart.products.size());



    }



}
