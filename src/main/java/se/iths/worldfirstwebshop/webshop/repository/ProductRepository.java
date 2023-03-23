package se.iths.worldfirstwebshop.webshop.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.iths.worldfirstwebshop.webshop.product.ProductEntity;

public interface ProductRepository extends ListCrudRepository<ProductEntity, Long> {
}
