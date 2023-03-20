package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cart {

    private final Map<Product, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void add(Product product, int amount) {
        if (products.containsKey(product)) {
            int amountInStock = products.get(product);
            products.put(product, amount + amountInStock);
        } else {
            products.put(product, amount);
        }
    }

    public int getAmountInCart(Product product) {
        return Objects.requireNonNullElse(products.get(product), 0);
    }

    public void clear() {
        products.clear();
    }

    public void remove(Product product, int amount) {
        if (!this.products.containsKey(product))
            return;

        if (products.get(product) < amount)
            removeFromCart(product);
        else
            removeAmountFromCart(product, amount);
    }

    private void removeFromCart(Product product) {
        products.remove(product);
    }

    private void removeAmountFromCart(Product product, int amount) {
        int currentAmount = this.products.get(product);
        this.products.put(product, currentAmount - amount);
    }

}
