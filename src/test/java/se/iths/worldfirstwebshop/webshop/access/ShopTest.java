package se.iths.worldfirstwebshop.webshop.access;

import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

     Shop shop = new Shop();

    @Test
    void addToCartWithProductWith5InStockShouldReturn2WhenBuying3(){
        var product = new Product("test", BigDecimal.ONE, 5);
        shop.getInventory().add(product);

        shop.addToCart(product, 3);
        var list = shop.getCart().getCart();
        var leftInStock = shop.getCart().getCart().get(0).getNumberInStock();

        assertThat(leftInStock).isEqualTo(2);
    }

}