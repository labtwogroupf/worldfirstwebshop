package se.iths.worldfirstwebshop.webshop.storage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;

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

}
