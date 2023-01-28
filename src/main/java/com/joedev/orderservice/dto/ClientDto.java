package com.joedev.orderservice.dto;


public record ClientDto(
        Long id,
        String name,
        String address
) { }
