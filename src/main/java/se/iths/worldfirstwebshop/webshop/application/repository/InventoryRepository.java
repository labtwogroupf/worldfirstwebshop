package se.iths.worldfirstwebshop.webshop.application.repository;
import org.springframework.data.repository.ListCrudRepository;
import se.iths.worldfirstwebshop.webshop.application.entity.InventoryEntity;


public interface InventoryRepository extends ListCrudRepository<InventoryEntity, Long> {


}
