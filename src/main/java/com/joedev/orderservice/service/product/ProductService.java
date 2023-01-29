package com.joedev.orderservice.service.product;

import com.joedev.orderservice.dto.ProductDto;
import java.util.List;

public interface ProductService {
    void saveProduct(ProductDto productDto);
    void saveProducts(List<ProductDto> productDtoList);
    void updateProduct(ProductDto productDto);
    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProducts();
}
