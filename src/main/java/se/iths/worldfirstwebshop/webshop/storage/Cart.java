package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Cart {

    private final Map<Product, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public void add(Product product, int amount) {
        if (products.containsKey(product)) {
            int amountInStock = products.get(product);
            products.put(product, amount + amountInStock);
        } else {
            products.put(product, amount);
        }
    }

    public void clear() {
        products.clear();
    }

    public int getNumberInCart(Product product) {
        return Objects.requireNonNullElse(products.get(product), 0);
    }


}
