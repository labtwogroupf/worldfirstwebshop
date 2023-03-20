package se.iths.worldfirstwebshop.webshop.product;
import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private int amountInStock;
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    public Product(String name, BigDecimal price, int numberInStock, String isbn) {
        this.name = name;
        this.price = price;
        this.amountInStock = numberInStock;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public int getAmountInStock() {
        return amountInStock;
    }
}
