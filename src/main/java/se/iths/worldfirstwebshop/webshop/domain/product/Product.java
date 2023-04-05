package se.iths.worldfirstwebshop.webshop.domain.product;

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

    public Product(Product product) {
        this.name = product.name;
        this.id = product.id;
        this.price = product.price;
        this.isbn = product.isbn;
    }

    public Long getId() {
        return id;
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
