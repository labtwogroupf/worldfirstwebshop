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

        var inventoryProduct = inventory.getProduct(product);
        var cartProduct = cart.getProduct(product);
        int maxPossibleAmount = Math.min(amount, inventoryProduct.getAmountInStock());

        if (cartProduct == null) {
            cartProduct = new Product(inventoryProduct.getName(),
                    inventoryProduct.getPrice(), 0,inventoryProduct.getIsbn());
            cart.add(cartProduct);
        }

        cartProduct.setAmountInStock(cartProduct.getAmountInStock() + maxPossibleAmount);
        inventoryProduct.setAmountInStock(inventoryProduct.getAmountInStock() - maxPossibleAmount);

    }

    public void removeFromCart(Product product, int amount) {

        var cartProduct = cart.getProduct(product);
        var inventoryProduct = inventory.getProduct(product);
        int maxPossibleAmount = Math.min(amount, cartProduct.getAmountInStock());

        inventoryProduct.setAmountInStock(inventoryProduct.getAmountInStock() + maxPossibleAmount);
        cartProduct.setAmountInStock(cartProduct.getAmountInStock() - maxPossibleAmount);

        if(cartProduct.getAmountInStock() == 0)
            cart.remove(cartProduct);

    }

    public Cart getCart() {
        return cart;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
