package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart{

    private final List<Product> products = new ArrayList<>();

    public void add(Product product){
        products.add(product);

    }

    public void print(){
        products.forEach(System.out::println);
    }


    public BigDecimal totalPrice(){
        return products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getAmountInStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void clear(){
        products.clear();
    }


    public void remove(Product product) {
        products.remove(product);
    }


    public void remove(Long id){

        //Add later when we got id
        products.remove(Math.toIntExact(id));

    }

    public int getSize(){
        return products.size();
    }

    public List<Product> getCart(){
        return products;
    }


    public Product getProduct(Product product) {
        return products.stream()
                .filter(p -> p.getIsbn().equals(product.getIsbn()))
                .findFirst()
                .orElse(null);
    }
}
