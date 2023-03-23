package se.iths.worldfirstwebshop.webshop.dto;

import org.jetbrains.annotations.NotNull;

public class InventoryDto {

    @NotNull
    private int amount;
    @NotNull
    private ProductDto product;

    public InventoryDto(int amount, ProductDto product) {
        this.amount = amount;
        this.product = product;
    }

    public InventoryDto() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
