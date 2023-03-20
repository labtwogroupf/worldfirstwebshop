package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory{

    private final List<Product> inventory = new ArrayList<>();

    public List<Product> getInventory() {
        return inventory;
    }

    public void add(Product product) {
        inventory.add(product);
    }

    public void remove(Product product) {
        inventory.remove(product);

    }

    public void remove(Long id) {
        //add remove by id later, now removes index
        inventory.remove(Math.toIntExact(id));
    }


    public void print() {
        inventory.forEach(product -> System.out.println(product.getName()));
    }


    public BigDecimal totalPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : inventory) sum = sum.add(product.getPrice());
        return sum;
    }


    public void clear() {
        inventory.clear();
    }

    public Product getProduct(int i) {
        return inventory.get(i);
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
