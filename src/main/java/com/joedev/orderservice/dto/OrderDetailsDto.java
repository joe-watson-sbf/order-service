package com.joedev.orderservice.dto;

public record OrderDetailsDto(
        ProductDto productDto, Integer quantity
) { }
