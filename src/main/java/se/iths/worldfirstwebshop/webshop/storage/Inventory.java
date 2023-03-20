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

    public void clear() {
        inventory.clear();
    }

    public int getNrOfProductsInStock(Product product) {
        return inventory.get(product);
    }

    public int size() {

        return inventory.size();
    }


    public boolean contains(Product product){
        return inventory.keySet().stream().anyMatch(product1 -> product1.matchingIsbn(product.getIsbn()));
    }

}
