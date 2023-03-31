package se.iths.worldfirstwebshop.webshop.dto;

import lombok.Getter;

import org.jetbrains.annotations.NotNull;

@Getter

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

}
