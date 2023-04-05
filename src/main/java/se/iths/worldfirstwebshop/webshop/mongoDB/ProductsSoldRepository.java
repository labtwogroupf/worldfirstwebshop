package se.iths.worldfirstwebshop.webshop.mongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsSoldRepository extends MongoRepository<ProductsSold, String> {

}
