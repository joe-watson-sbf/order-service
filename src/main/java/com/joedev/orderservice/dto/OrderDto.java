package com.joedev.orderservice.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderDto(Long id, Long clientId,
                       List<OrderDetailsDto> orderDetailsDto,
                       Instant createdOn, BigDecimal totalPrice) { }
