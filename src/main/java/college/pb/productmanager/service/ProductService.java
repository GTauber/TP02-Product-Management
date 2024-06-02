package college.pb.productmanager.service;

import college.pb.productmanager.model.entity.Product;
import college.pb.productmanager.model.dto.ProductDto;
import java.util.List;

public interface ProductService {

    Product createProduct(ProductDto product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(ProductDto product);
    void deleteProductById(Long id);

}
