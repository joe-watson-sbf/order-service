package com.joedev.orderservice.service.order;

import com.joedev.orderservice.dto.OrderCommand;
import com.joedev.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService{
    void createOrder(OrderCommand command);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getAllOrders();

}
