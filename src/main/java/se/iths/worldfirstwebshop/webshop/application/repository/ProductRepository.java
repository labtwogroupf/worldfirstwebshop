package se.iths.worldfirstwebshop.webshop.application.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.iths.worldfirstwebshop.webshop.application.entity.ProductEntity;

public interface ProductRepository extends ListCrudRepository<ProductEntity, Long> {
}
