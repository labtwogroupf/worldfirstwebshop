package se.iths.worldfirstwebshop.webshop.access;

import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

import java.util.Map;

public class Shop {

    private final Cart cart;
    private final Inventory inventory;

    public Shop() {
        this.cart = new Cart();
        this.inventory = new Inventory();
    }

    public Shop(Map<Product, Integer> inventories) {
        this.cart = new Cart();
        this.inventory = new Inventory(inventories);
    }

    public void addToCart(Product product, int amount) {
        if (checkIfInStock(product, amount))
            cart.add(product, amount);
    }

    public void checkout() {
        cart.getProducts()
                .keySet()
                .forEach(this::removeBoughtItemsFromInventory);
        cart.clear();
    }

    public void removeFromCart(Product product) {
        cart.remove(product, -1);

    }

    private boolean checkIfInStock(Product product, int amount) {
        if (!inventory.contains(product))
            return false;

        var inventoryStock = inventory.getNrOfProductsInStock(product);
        var cartStock = cart.getAmountInCart(product);

        return amount <= inventoryStock - cartStock;
    }

    public Cart getCart() {
        return cart;
    }

    public Inventory getInventory() {
        return inventory;
    }

    private void removeBoughtItemsFromInventory(Product product) {
        inventory.remove(product, cart.getAmountInCart(product));
    }
}
