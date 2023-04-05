package se.iths.worldfirstwebshop.webshop.domain.inventory;

import org.jetbrains.annotations.NotNull;
import se.iths.worldfirstwebshop.webshop.domain.product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Inventory {

    private final Map<Product, Integer> inventory = new HashMap<>();

    public Inventory() {
    }

    public Inventory(Map<Product, Integer> inventoryDto) {
        inventory.putAll(inventoryDto);
    }

    public void add(Product product, int amount) {
        inventory.put(product, amount);
    }

    public int getNrOfProductsInStock(Product product) {
        return inventory.get(product);
    }

    public boolean contains(Product product) {
        return inventory.containsKey(product);
    }

    public void remove(Product product, int amount) {
        if (this.inventory.containsKey(product))
            removeAmountFromInventory(product, amount);
    }

    private void removeAmountFromInventory(Product product, int amountToRemove) {
        int currentAmount = this.inventory.get(product);
        this.inventory.put(product, currentAmount - amountToRemove);
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }

    public Product getProductInInventory(Product product) {
        return inventory.keySet()
                .stream()
                .filter(matchingIsbn(product))
                .findFirst()
                .orElse(null);
    }

    @NotNull
    private static Predicate<Product> matchingIsbn(Product product) {
        return key -> key.matchingIsbn(product.getIsbn());
    }
}
