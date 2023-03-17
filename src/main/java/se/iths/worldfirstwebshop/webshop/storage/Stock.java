package se.iths.worldfirstwebshop.webshop.storage;


import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;


public interface Stock {

    void add(Product product);

    void remove(Product product);

    void remove(Long id);

    void print();

    BigDecimal totalPrice();

    void clear();







}
