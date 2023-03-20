package se.iths.worldfirstwebshop.webshop.access;
import org.junit.jupiter.api.Test;
import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;


class ShopTest {

    Shop shop = new Shop();
    Inventory inventory = shop.getInventory();
    Cart cart = shop.getCart();

    @Test
    void add3toCartShouldAdd3(){
        var product = new Product("test", BigDecimal.ONE, "1");
        inventory.add(product, 5);

        cart.add(product, 3);
        var leftInStock = cart.getAmountInCart(product);

        assertThat(leftInStock).isEqualTo(3);
    }





    @Test
    void remove2FromStockRemovesCorrectAmountAndReturns1(){
        var product = new Product("test", BigDecimal.ONE, "1");
        inventory.add(product, 5);
        shop.addToCart(product, 3);
        cart.remove(product, 2);

        var leftInStock = cart.getAmountInCart(product);


        assertThat(leftInStock).isEqualTo(1);



    }


    @Test
    void add2Than2ToShouldReturn4() {

        var product = new Product("test", BigDecimal.ONE, "1");
        inventory.add(product, 5);
        shop.addToCart(product, 2);
        shop.addToCart(product, 2);
        var amountInCart = cart.getAmountInCart(product);

        assertThat(amountInCart).isEqualTo(4);
    }

    @Test
    void addingAProductWith5InStockAndBuying3ShouldResultIn2LeftInInventory(){
        var product = new Product("Äpple", BigDecimal.ONE, "1");

        inventory.add(product, 5);
        cart.add(product, 3);

        shop.checkout();

        assertThat(inventory.getNrOfProductsInStock(product)).isEqualTo(2);

    }




    @Test
    void remove4thanRemove2ShouldReturn3(){

        var product = new Product("Svartvinbär", BigDecimal.ONE,  "1");
        inventory.add(product, 5);
        shop.addToCart(product, 4);
        cart.remove(product, 2);
        shop.checkout();
        var leftInInventory = inventory.getNrOfProductsInStock(product);

        assertThat(leftInInventory).isEqualTo(3);

    }

}
