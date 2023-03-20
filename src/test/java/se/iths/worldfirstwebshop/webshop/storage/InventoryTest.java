package se.iths.worldfirstwebshop.webshop.storage;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InventoryTest {

    Inventory inventory = new Inventory();

    @Test
    void addProductToInventoryAddsProduct() {

        var testProduct = new Product("test", BigDecimal.ONE,"100");

        inventory.add(testProduct);

        assertThat(inventory.getNrOfProductsInStock().isEqualTo(testProduct);

    }

    @Test
    void containsShouldReturnTrueFalseIfThereIsNoMathingIsbn() {
        Product product = new Product("Svart te", BigDecimal.ONE, "1234");
        inventory.add(product, 10);

        assertThat(inventory.contains(new Product("Svart Te", BigDecimal.ONE, "12345"))).isFalse();

    }

    @Test
    void removingMoreThanIsIn() {

    }

}

