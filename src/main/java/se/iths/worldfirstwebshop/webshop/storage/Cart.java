package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
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
        products.put(product, amount);
        //för varje product i hashmap
        //inventory.get(hashmap key(äpple)).setAmount(inventory.get(hashmap key(äpple) - hashmap.getvalue(äpple))

    }

    public void clear() {
        products.clear();
    }

    public void remove(Long id) {

        //Add later when we got id
        products.remove(Math.toIntExact(id));

    }

    public int getSize() {
        return products.size();
    }

    public List<Product> getCart() {
        return products.keySet().stream().toList();
    }

    public boolean contains(Product product) {
        return products.containsKey(product);
    }



    public Product returnProduct(Product product) {
//        for (Product value : products)
//            if (value.equals(product))
//                return value;
//
        return null;
    }

    public int getNumberInCart(Product product) {
        return Objects.requireNonNullElse(products.get(product), 0);
    }
}
