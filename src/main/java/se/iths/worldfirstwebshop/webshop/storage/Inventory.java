package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Stock{

    private final List<Product> inventory = new ArrayList<>();

    @Override
    public void add(Product product) {



    }

    @Override
    public void remove() {

    }

    @Override
    public void print() {

    }

    @Override
    public BigDecimal totalPrice() {
        return null;
    }

    @Override
    public void clear() {

    }
}
