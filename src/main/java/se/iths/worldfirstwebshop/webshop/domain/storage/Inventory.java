package se.iths.worldfirstwebshop.webshop.domain.storage;

import se.iths.worldfirstwebshop.webshop.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class Inventory{

    private final Map<Product, Integer> inventory = new HashMap<>();


    public void add(Product product, int amount) {
        inventory.put(product, amount);
    }



    public int getNrOfProductsInStock(Product product) {
        return inventory.get(product);
    }


    public boolean contains(Product product){
        return inventory.keySet().stream().anyMatch(product1 -> product1.matchingIsbn(product.getIsbn()));
    }

    public void remove(Product product, int amount) {
        if (!this.inventory.containsKey(product))
            return;

        removeAmountFromInventory(product, amount);
    }

    private void removeAmountFromInventory(Product product, int amount) {
        int currentAmount = this.inventory.get(product);
        this.inventory.put(product, currentAmount - amount);
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }
}
