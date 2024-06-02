package college.pb.productmanager.service.impl;

import college.pb.productmanager.model.entity.Product;
import college.pb.productmanager.model.dto.ProductDto;
import college.pb.productmanager.repository.ProductRepository;
import college.pb.productmanager.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ConversionService conversionService;
    private final ProductRepository productRepository;


    @Override
    public Product createProduct(ProductDto product) {
        log.info("Creating product: {}", product);
        var newProduct = conversionService.convert(product, Product.class);
        if (newProduct == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(Long id) {
        log.info("Getting product by id: {}", id);
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(ProductDto productDto) {
        log.info("Updating product: {}", productDto);
        var existingProduct = productRepository.findById(productDto.id()).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        BeanUtils.copyProperties(productDto, existingProduct);
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        log.info("Deleting product by id: {}", id);
        productRepository.deleteById(id);
    }
}
