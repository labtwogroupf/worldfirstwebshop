package se.iths.worldfirstwebshop.webshop.storage;


import java.math.BigDecimal;


public interface Stock {

    void add();

    void remove();

    void print();

    BigDecimal totalPrice();

    void clear();







}
