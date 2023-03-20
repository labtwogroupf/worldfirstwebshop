package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory{

    private final Map<Product, Integer> inventory = new HashMap<>();

    public Map<Product, Integer> getInventory() {
        return inventory;
    }

    public void add(Product product, int amount) {
        inventory.put(product, amount);
    }

    public void remove(Product product) {
        inventory.remove(product);

    }

    public void remove(Long id) {
        //add remove by id later, now removes index
        inventory.remove(Math.toIntExact(id));
    }


    public void clear() {
        inventory.clear();
    }

    public int getNrOfProductsInStock(Product product) {
        return inventory.get(product);
    }

    public Product getProduct(Product product) {
        

        return inventory.stream()
                .filter(p -> p.getIsbn().equals(product.getIsbn()))
                .findFirst()
                .orElse(null);
    }


    public int size() {

        return inventory.size();
    }




}
