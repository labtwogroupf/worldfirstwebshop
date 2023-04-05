package se.iths.worldfirstwebshop.webshop.application.mongodb;

import org.springframework.web.bind.annotation.*;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sold")
public class ProductSoldController {

    MongoDbService mongoRepo;

    public ProductSoldController(MongoDbService mongo) {
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

    @GetMapping("/getAll")
    List<ProductsSold> getAllSoldProducts(){
        return mongoRepo.getAll();
    }
}
