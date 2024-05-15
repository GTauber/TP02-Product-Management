package college.pb.productmanager.converter;

import college.pb.productmanager.model.entity.Product;
import college.pb.productmanager.model.entity.ProductDto;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(@Nonnull Product product) {
        return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .category(product.getCategory())
            .brand(product.getBrand())
            .image(product.getImage())
            .status(product.getStatus())
            .build();
    }
}
