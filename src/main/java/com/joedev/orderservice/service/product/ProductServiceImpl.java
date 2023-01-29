package com.joedev.orderservice.service.product;

import com.joedev.orderservice.dto.ProductDto;
import com.joedev.orderservice.entity.Product;
import com.joedev.orderservice.handler.BusinessException;
import com.joedev.orderservice.mapper.DataMapper;
import com.joedev.orderservice.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.joedev.orderservice.utils.ServiceUtils.requiredNonNonEmptyList;
import static com.joedev.orderservice.utils.ServiceUtils.requiredNonNullObject;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final DataMapper mapper;

    public ProductServiceImpl(ProductRepository repository, DataMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        requiredNonNullObject(productDto);
        repository.save(mapper.dtoToEntity(productDto));
    }

    @Override
    public void saveProducts(List<ProductDto> productDtoList) {
        requiredNonNonEmptyList(productDtoList);
        List<Product> list = productDtoList
                .stream()
                .map(mapper::dtoToEntity)
                .toList();
        repository.saveAll(list);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        requiredNonNullObject(productDto);
        repository.save(mapper.dtoToEntity(productDto));
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return mapper.entityToDto(getProduct(productId));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return repository.findAll()
                .stream().map(mapper::entityToDto)
                .toList();
    }


    private Product getProduct(Long productId){
        return repository
                .findById(productId)
                .orElseThrow(()->
                        new BusinessException("Product with id " + productId
                        + " not found!", HttpStatus.NOT_FOUND));
    }


}
