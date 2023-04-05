package se.iths.worldfirstwebshop.webshop.mongoDB;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class MongoDbService {

    ProductsSoldRepository productsSoldRepo;

    public MongoDbService(ProductsSoldRepository productsSoldRepo) {
        this.productsSoldRepo = productsSoldRepo;
    }

    public void addNumberSold(ProductDto product, int productsSold) {
        productsSoldRepo
                .findAll()
                .stream()
                .filter(getSameProduct(product))
                .findFirst()
                .ifPresentOrElse(updateExistingProductSold(productsSold), saveNewProductSold(product, productsSold));
    }

    public Optional<ProductsSold> getAmountOfProductSold(ProductDto productDto) {
        return productsSoldRepo
                .findAll()
                .stream()
                .filter(getSameProduct(productDto))
                .findFirst();
    }
    public List<ProductsSold> getAll(){
        return productsSoldRepo.findAll();
    }

    @NotNull
    private Consumer<ProductsSold> updateExistingProductSold(int productsSold) {
        return p -> {
            p.setProductsSold(p.getProductsSold() + productsSold);
            productsSoldRepo.save(p);
        };
    }

    @NotNull
    private Runnable saveNewProductSold(ProductDto product, int productsSold) {
        return () -> productsSoldRepo.save(new ProductsSold(product, productsSold));
    }

    @NotNull
    private static Predicate<ProductsSold> getSameProduct(ProductDto productDto) {
        return p -> p.getProduct().isSame(productDto);
    }

}
