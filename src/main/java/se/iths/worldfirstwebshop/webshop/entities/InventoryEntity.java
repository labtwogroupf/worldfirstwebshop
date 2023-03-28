package se.iths.worldfirstwebshop.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
