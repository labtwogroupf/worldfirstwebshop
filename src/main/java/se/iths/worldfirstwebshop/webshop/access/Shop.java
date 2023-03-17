package se.iths.worldfirstwebshop.webshop.access;

import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

public class Shop {

    private final Cart cart;
    private final Inventory inventory;


    public Shop(){
        this.cart = new Cart();
        this.inventory = new Inventory();
    }

    public void addToCart(Product product, int amount){
        var p = inventory.getProduct(product);
        if(p != null) {
            int numberInStock = p.getNumberInStock();
            if (numberInStock <= amount) {
                var newProduct = new Product(p.getName(), p.getPrice(), p.getNumberInStock()-amount);
                this.cart.add(newProduct);
            }
        }
    }



}
