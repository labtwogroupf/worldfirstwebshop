package se.iths.worldfirstwebshop.webshop.storage;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    Cart products = new Cart();

    @Test
    void having3InCartAndAdding3MoreShouldAddTotalAmountTo6() {
        Product product = new Product("Svart te", BigDecimal.ONE, "1234");

        products.add(product, 3);
        products.add(product, 3);

        assertThat(products.getAmountInCart(product)).isEqualTo(6);

    }

    @Test
    void adding3ShouldReturn3InTotalAmountIfThereIsAnEmptyCart() {

        Product product = new Product("Svart te", BigDecimal.ONE, "1234");

        products.add(product, 3);

        assertThat(products.getAmountInCart(product)).isEqualTo(3);

    }

    @Test
    void removingMoreThanIsInCartRemovesProduct() {

        Product product = new Product("Svart te", BigDecimal.ONE, "1234");
        products.add(product, 5);
        products.remove(product, 10);

        assertThat(products.getProducts().containsKey(product)).isFalse();
    }

}
