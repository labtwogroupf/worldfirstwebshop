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

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }
}
