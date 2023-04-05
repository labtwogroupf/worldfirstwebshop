package se.iths.worldfirstwebshop.webshop.storage;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.domain.inventory.Cart;
import se.iths.worldfirstwebshop.webshop.domain.product.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    Cart products = new Cart();
    Product product = new Product("Svart te", BigDecimal.ONE, "1234");

    @Test
    void having3InCartAndAdding3MoreShouldAddTotalAmountTo6() {

        products.add(product, 3);
        products.add(product, 3);

        assertThat(products.getAmountInCart(product)).isEqualTo(6);
    }

    @Test
    void adding3ShouldReturn3InTotalAmountIfThereIsAnEmptyCart() {

        products.add(product, 3);

        assertThat(products.getAmountInCart(product)).isEqualTo(3);
    }

    @Test
    void removingMoreThanIsInCartRemovesProduct() {
        var prod = new Product(product);

        products.add(prod, 5);
        products.remove(prod, 10);

        assertThat(products.getProducts().containsKey(prod)).isFalse();
    }
}
