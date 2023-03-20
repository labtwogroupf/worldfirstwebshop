package se.iths.worldfirstwebshop.webshop.product;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public Product(String name, BigDecimal price, String isbn) {
        this.name = name;
        this.price = price;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }



    public boolean matchingIsbn(String isbn) {
        return this.isbn.equals(isbn);
    }
}
