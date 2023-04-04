package se.iths.worldfirstwebshop.webshop.dto;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    @NotNull
    String name;
    @NotNull
    BigDecimal price;
    @NotNull
    String isbn;

    public ProductDto(String name, BigDecimal price, String isbn, Long id) {
        this.name = name;
        this.price = price;
        this.isbn = isbn;
        this.id = id;
    }

    public ProductDto(String name, BigDecimal price, String isbn) {
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

    public ProductDto() {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public boolean isSame(ProductDto productDto){
        return this.isbn.equals(productDto.isbn);
    }
}
