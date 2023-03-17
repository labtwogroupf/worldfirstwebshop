package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Stock {

    private final List<Product> inventory = new ArrayList<>();

    @Override
    public void add(Product product) {
        inventory.add(product);
    }

    @Override
    public void remove(Product product) {
        inventory.remove(product);

    }

    @Override
    public void remove(Long id) {
        //add remove by id later, now removes index
        inventory.remove(id);
    }

    @Override
    public void print() {
        inventory.forEach(product -> System.out.println(product.getName()));
    }

    @Override
    public BigDecimal totalPrice() {
        BigDecimal sum = null;
        for (Product product : inventory) sum = product.getPrice();
        return sum;
    }


    @Override
    public void clear() {

        inventory.forEach(this::remove);
    }

    public Product getProduct(int i){
        return inventory.get(i);
    }
}
