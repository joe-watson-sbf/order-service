package com.joedev.orderservice.controller;

import com.joedev.orderservice.dto.*;
import com.joedev.orderservice.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(){
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/model")
    public ResponseEntity<OrderCommand> getModelOrder(){
        return ResponseEntity.ok(
                new OrderCommand(
                        new ClientDto(1L,
                                "Client Name",
                                "Client Address"),

                        List.of(new OrderDetailsDto(
                                new ProductDto(
                                        2L, "Product name",
                                        new BigDecimal(343), 34,
                                        false),
                                34)
                        )
                ));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(service.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> placeOrder(@RequestBody OrderCommand command){
        service.createOrder(command);
        return ResponseEntity.ok(new ResponseDto("Order placed successfully!"));
    }


}
