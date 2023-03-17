package se.iths.worldfirstwebshop.webshop.product;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private int numberInStock;

    public Product(String name, BigDecimal price, int numberInStock) {
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
    }

    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public int getNumberInStock() {
        return numberInStock;
    }


    public void increaseAmount(){
        numberInStock++;
    }

    public void decreaseAmount(){
        numberInStock--;
    }
}
