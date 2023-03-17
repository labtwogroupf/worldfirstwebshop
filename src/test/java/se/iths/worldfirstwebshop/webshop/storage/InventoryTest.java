package se.iths.worldfirstwebshop.webshop.storage;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InventoryTest {

    Inventory inventory = new Inventory();

    @Test
    void addProductToInventoryAddsProduct() {
        Product testProduct = new Product("test", BigDecimal.ONE, 100);

        inventory.add(testProduct);

        assertThat(inventory.getProduct(0)).isEqualTo(testProduct);
    }

}
