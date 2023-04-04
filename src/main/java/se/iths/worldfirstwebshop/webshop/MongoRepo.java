package se.iths.worldfirstwebshop.webshop;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.ProductsSold;
import se.iths.worldfirstwebshop.webshop.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.repository.mongo.ProductsSoldRepository;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class MongoRepo {

    ProductsSoldRepository productsSoldRepo;

    public MongoRepo(ProductsSoldRepository productsSoldRepo) {
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

    @NotNull
    private static Function<ProductsSold, Integer> mapToAmountSold() {
        return p -> p.productsSold;
    }
}
