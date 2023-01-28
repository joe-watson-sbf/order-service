package com.joedev.orderservice.mapper;

import com.joedev.orderservice.dto.ClientDto;
import com.joedev.orderservice.dto.OrderDetailsDto;
import com.joedev.orderservice.dto.OrderDto;
import com.joedev.orderservice.dto.ProductDto;
import com.joedev.orderservice.entity.Client;
import com.joedev.orderservice.entity.Order;
import com.joedev.orderservice.entity.OrderDetails;
import com.joedev.orderservice.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DataMapper {
    private final ModelMapper modelMapper;

    public DataMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDto entityToDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }
    public Product dtoToEntity(ProductDto productDto){
        return modelMapper.map(productDto, Product.class);
    }


    public ClientDto entityToDto(Client client){
        return modelMapper.map(client, ClientDto.class);
    }
    public Client dtoToEntity(ClientDto clientDto){
        return modelMapper.map(clientDto, Client.class);
    }


    public OrderDto entityToDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }
    public Order dtoToEntity(OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }

    public OrderDetailsDto entityToDto(OrderDetails orderDetails){
        return modelMapper.map(orderDetails, OrderDetailsDto.class);
    }
    public OrderDetails dtoToEntity(OrderDetailsDto orderDetailsDto){
        return modelMapper.map(orderDetailsDto, OrderDetails.class);
    }

}
