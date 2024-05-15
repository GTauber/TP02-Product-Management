package college.pb.productmanager.converter;

import college.pb.productmanager.model.entity.Product;
import college.pb.productmanager.model.entity.ProductDto;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProduct implements Converter<ProductDto, Product> {

    @Override
    public Product convert(@Nonnull ProductDto productDto) {
        return Product.builder()
            .name(productDto.name())
            .description(productDto.description())
            .price(productDto.price())
            .quantity(productDto.quantity())
            .category(productDto.category())
            .brand(productDto.brand())
            .image(productDto.image())
            .status(productDto.status())
            .build();
    }
}
