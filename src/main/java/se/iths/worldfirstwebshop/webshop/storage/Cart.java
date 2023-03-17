package se.iths.worldfirstwebshop.webshop.storage;

import se.iths.worldfirstwebshop.webshop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart implements Stock {


    public Cart(List<Product> products) {
        this.products = products;
    }

    List<Product> products = new ArrayList<>();

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
        products.remove(id);

    }
}
