package se.iths.worldfirstwebshop.webshop.application.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.application.mapper.Mapper;
import se.iths.worldfirstwebshop.webshop.application.entity.ProductEntity;
import se.iths.worldfirstwebshop.webshop.application.repository.ProductRepository;

import java.util.List;
import java.util.function.Function;

@Service
public class ProductService {
    ProductRepository productRepo;
    Mapper mapper;

    public ProductService(ProductRepository productRepo, Mapper mapper) {
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    public ProductDto getAProduct(long id) {
        return mapper.mapToDto(productRepo.findById(id).orElseThrow());
    }

    public void addProduct(ProductDto product) {
        productRepo.save(mapper.mapToEntity(product));
    }

    public List<ProductDto> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(mapToDto())
                .toList();
    }

    @NotNull
    private Function<ProductEntity, ProductDto> mapToDto() {
        return product -> mapper.mapToDto(product);
    }
}
