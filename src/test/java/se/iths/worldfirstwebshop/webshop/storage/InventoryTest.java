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
    void removeProductRemovesProductFromInventory(){
        /*var testProduct = new Product("test", BigDecimal.ONE, 100, "1");

        inventory.add(testProduct);
        inventory.remove(testProduct);

        assertThat(inventory.size()).isZero();


         */
    }
    @Test
    void removeProductWithIdRemovesCorrectProduct(){
/*        var testProduct = new Product("test", BigDecimal.ONE, 100, "1");
        var testProduct2 = new Product("test2", BigDecimal.ONE, 100, "2");

        inventory.add(testProduct);
        inventory.add(testProduct2);
        inventory.remove(0L);

        assertThat(inventory.getProduct(0)).isEqualTo(testProduct2);

 */


    }
    @Test
    void totalPriceReturnsCorrectAmount(){
        /*var testProduct = new Product("test", BigDecimal.ONE, 100, "1");
        var testProduct2 = new Product("test", BigDecimal.ONE, 100,"2");

        inventory.add(testProduct);
        inventory.add(testProduct2);

        var totalPrice = inventory.totalPrice();

        assertThat(totalPrice).isEqualTo(BigDecimal.TWO);

         */
    }

    @Test
    void callingClearMethodClearsInventoryFromProducts(){
        /*var testProduct = new Product("test", BigDecimal.ONE, 100, "1");
        var testProduct2 = new Product("test", BigDecimal.ONE, 100, "2");
        var testProduct3 = new Product("test", BigDecimal.ONE, 100, "3");
        var testProduct4 = new Product("test", BigDecimal.ONE, 100, "4");

        inventory.add(testProduct);
        inventory.add(testProduct2);
        inventory.add(testProduct3);
        inventory.add(testProduct4);
        inventory.clear();

        assertThat(inventory.size()).isZero();

         */

    }

}
