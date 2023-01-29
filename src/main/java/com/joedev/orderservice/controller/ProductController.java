package com.joedev.orderservice.controller;

import com.joedev.orderservice.dto.ProductDto;
import com.joedev.orderservice.dto.ResponseDto;
import com.joedev.orderservice.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(service.getProductById(productId));
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseDto> saveProducts(@RequestBody List<ProductDto> productDtoList){
        service.saveProducts(productDtoList);
        return ResponseEntity.ok(new ResponseDto("Products saved successfully!"));
    }

}
