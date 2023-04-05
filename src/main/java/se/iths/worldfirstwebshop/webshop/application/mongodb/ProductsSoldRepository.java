package se.iths.worldfirstwebshop.webshop.application.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsSoldRepository extends MongoRepository<ProductsSold, String> {

}
