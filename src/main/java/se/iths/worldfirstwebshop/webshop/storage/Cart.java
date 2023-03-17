package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart implements Stock {

    private final List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }


    @Override
    public void add(Product product){
        products.add(product);

    }

    @Override
    public void print(){
        products.forEach(System.out::println);
    }

    @Override
    public BigDecimal totalPrice(){
        return products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getNumberInStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void clear(){
        products.clear();
    }

    @Override
    public void remove(Product product) {

        products.remove(product);
    }


    @Override
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

    public boolean contains(Product product) {
        return products.contains(product);
    }


    public void decreaseAmount(Product product){
        returnProduct(product).decreaseAmount();
    }
    public void increaseAmount(Product product){
        returnProduct(product).increaseAmount();
    }

    public Product returnProduct(Product product){
        for (Product value : products)
            if (value.equals(product))
                return value;

        return null;
    }

}
