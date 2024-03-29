package se.iths.worldfirstwebshop.webshop.application.mongodb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;

@Getter
@Setter
@Document
public class ProductsSold {
    public String id;
    public ProductDto product;
    public int productsSold;

    public ProductsSold(ProductDto product, int productsSold) {
        this.product = product;
        this.productsSold = productsSold;
    }
}
