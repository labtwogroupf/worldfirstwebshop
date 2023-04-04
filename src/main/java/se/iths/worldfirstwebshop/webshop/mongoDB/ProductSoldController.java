package se.iths.worldfirstwebshop.webshop.mongoDB;

import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;

import java.util.Optional;

@RestController
@RequestMapping("/api/sold")
public class ProductSoldController {

    MongoRepo mongoRepo;

    public ProductSoldController(MongoRepo mongo) {
        this.mongoRepo = mongo;
    }
    @GetMapping()
    Optional<ProductsSold> getAmountSold(@RequestBody ProductDto product){
        return mongoRepo.getAmountOfProductSold(product);
    }

    @PostMapping
    void addAmountSold(@RequestBody ProductDto productDto, @RequestParam int amount){
        mongoRepo.addNumberSold(productDto, amount);
    }
}
