package se.iths.worldfirstwebshop.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    BigDecimal price;
    String isbn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return Objects.equals(id, productEntity.id);
    }

    @Override
    public int hashCode() {
        return 97;
    }
}
