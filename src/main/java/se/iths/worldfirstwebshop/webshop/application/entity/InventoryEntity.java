package se.iths.worldfirstwebshop.webshop.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import se.iths.worldfirstwebshop.webshop.domain.product.Product;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Inventory.products",
        attributeNodes = @NamedAttributeNode("product"))
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;

    @OneToOne
    private ProductEntity product;

    public InventoryEntity(Product product, int i) {
    }

    public InventoryEntity() {

    }

}
