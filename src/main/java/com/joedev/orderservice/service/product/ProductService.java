package com.joedev.orderservice.service.product;

import com.joedev.orderservice.dto.ProductDto;
import java.util.List;

public interface ProductService {
    void saveProduct(ProductDto productDto);
    void saveProducts(List<ProductDto> productDtoList);
    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProducts();
}
