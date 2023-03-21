package se.iths.worldfirstwebshop.webshop.storage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;
import java.util.*;

@Entity
@Getter
@Setter
@NamedEntityGraph(name ="Inventory.products",
attributeNodes = @NamedAttributeNode("products"))
public class InventoryEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;

    @OneToMany
    private final Set<ProductEntity> products = new HashSet<>();

}
