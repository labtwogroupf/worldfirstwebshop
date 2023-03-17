package se.iths.worldfirstwebshop.webshop.access;

import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

public class Shop {

    private final Cart cart;
    private final Inventory inventory;


    public Shop(){
        this.cart = new Cart();
        this.inventory = new Inventory();
    }


}
