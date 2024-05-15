package college.pb.productmanager.controller;

import college.pb.productmanager.model.Response;
import college.pb.productmanager.model.entity.Product;
import college.pb.productmanager.model.entity.ProductDto;
import college.pb.productmanager.service.ProductService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ConversionService conversionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Received request to create new Product: [{}]", productDto);
        var newProductDto = conversionService.convert(productService.createProduct(productDto), ProductDto.class);
        return Response.<ProductDto>builder()
            .statusCode(HttpStatus.CREATED.value())
            .status(HttpStatus.CREATED)
            .message("Product created successfully")
            .data(Map.of("product", newProductDto))
            .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<ProductDto> getProductById(@RequestParam Long id) {
        log.info("Received request to get Product by id: [{}]", id);
        var productDto = conversionService.convert(productService.getProductById(id), ProductDto.class);
        return Response.<ProductDto>builder()
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Product retrieved successfully")
            .data(Map.of("product", productDto))
            .build();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<ProductDto>> getAllProducts() {
        log.info("Received request to get all Products");
        var productDtos = this.convertResponse(productService.getAllProducts());
        return Response.<List<ProductDto>>builder()
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Products retrieved successfully")
            .data(Map.of("products", productDtos))
            .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        log.info("Received request to update Product: [{}]", productDto);
        var updatedProductDto = conversionService.convert(productService.updateProduct(productDto), ProductDto.class);
        return Response.<ProductDto>builder()
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Product updated successfully")
            .data(Map.of("product", updatedProductDto))
            .build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response<ProductDto> deleteProductById(@RequestParam Long id) {
        log.info("Received request to delete Product by id: [{}]", id);
        productService.deleteProductById(id);
        return Response.<ProductDto>builder()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .status(HttpStatus.NO_CONTENT)
            .message("Product deleted successfully")
            .build();
    }

    @SuppressWarnings("unchecked")
    private List<ProductDto> convertResponse(List<Product> products) {
        var sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Product.class));
        var targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ProductDto.class));
        return (List<ProductDto>) conversionService.convert(products, sourceType, targetType);
    }

}
