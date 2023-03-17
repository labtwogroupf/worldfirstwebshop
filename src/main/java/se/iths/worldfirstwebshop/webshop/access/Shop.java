package se.iths.worldfirstwebshop.webshop.access;

import se.iths.worldfirstwebshop.webshop.product.Product;
import se.iths.worldfirstwebshop.webshop.storage.Cart;
import se.iths.worldfirstwebshop.webshop.storage.Inventory;

public class Shop {

    private final Cart cart;
    private final Inventory inventory;

    public Shop() {
        this.cart = new Cart();
        this.inventory = new Inventory();
    }

    public void addToCart(Product product, int amount) {
        var p = inventory.getProduct(product);
        if (p != null) {
            int numberInStock = p.getNumberInStock();
            if (numberInStock >= amount) {
                var newProduct = new Product(p.getName(), p.getPrice(), 0);
                this.cart.add(newProduct);

                for (int i = 0; i < amount; i++) {
                    this.inventory.decreaseAmount(p);
                    this.cart.increaseAmount(newProduct);
                }

            }
        }
    }

    private void fromInventoryToCart(int amount, Product p) {
        for (int i = 0; i < amount; i++) {
            this.inventory.decreaseAmount(p);
            this.cart.increaseAmount(p);
        }
    }

    public void removeFromCart(Product product, int amount) {
        if (this.cart.contains(product)) {
            for (int i = 0; i < amount; i++)
                this.cart.decreaseAmount(product);

        }
    }

    public Cart getCart() {
        return cart;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
