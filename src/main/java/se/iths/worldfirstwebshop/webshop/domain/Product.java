package se.iths.worldfirstwebshop.webshop.domain;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private String isbn;

    public Product(String name, BigDecimal price, String isbn) {
        this.name = name;
        this.price = price;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public Product(String name, BigDecimal price, String isbn, Long id) {
        this.name = name;
        this.price = price;
        this.isbn = isbn;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean matchingIsbn(String isbn) {
        return this.isbn.equals(isbn);
    }
}
