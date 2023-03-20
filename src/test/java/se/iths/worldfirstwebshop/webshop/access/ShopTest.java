package se.iths.worldfirstwebshop.webshop.access;
import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;


class ShopTest {

     Shop shop = new Shop();

    @Test
    void addToCartWithProductWith5InStockShouldReturn2WhenBuying3(){
        var product = new Product("test", BigDecimal.ONE, 5, "1");
        shop.getInventory().add(product);

        shop.addToCart(product, 3);
        var leftInStock = shop.getInventory().getProduct(0).getAmountInStock();

        assertThat(leftInStock).isEqualTo(2);
    }

    @Test
    void remove2FromStockRemovesCorrectAmountAndReturns1(){
        var product = new Product("test", BigDecimal.ONE, 5, "1");
        shop.getInventory().add(product);

        shop.addToCart(product, 3);
        shop.removeFromCart(shop.getCart().getCart().get(0), 2);

        var leftInStock = shop.getCart().getCart().get(0).getAmountInStock();

        assertThat(leftInStock).isEqualTo(1);

    }

    @Test
    void add2Than2ToShouldReturn4(){

        var product = new Product("test", BigDecimal.ONE, 5, "1");
        shop.getInventory().add(product);
        shop.addToCart(product, 2);
        shop.addToCart(product, 2);
        var amountInCart = shop.getCart().getCart().get(0).getAmountInStock();

        assertThat(amountInCart).isEqualTo(4);
    }
    @Test
    void remove4thanRemove2ShouldReturn3(){

        var product = new Product("test", BigDecimal.ONE, 5, "1");
        shop.getInventory().add(product);
        shop.addToCart(product, 4);
        shop.removeFromCart(product, 2);
        var leftInInventory = shop.getInventory().getInventory().get(0).getAmountInStock();

        assertThat(leftInInventory).isEqualTo(3);

    }
}
