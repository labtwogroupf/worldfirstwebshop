package se.iths.worldfirstwebshop.webshop.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.iths.worldfirstwebshop.ProductsSold;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;

public interface ProductsSoldRepository extends MongoRepository<ProductsSold, String> {

}
