package com.joedev.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long clientId;
    private List<OrderDetailsDto> orderDetailsDto;
    private Instant createdOn;
    private BigDecimal totalPrice;


}
