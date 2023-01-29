package com.joedev.orderservice.dto;

import java.util.List;

public record OrderCommand(ClientDto clientDto, List<OrderDetailsDto> orderDetailsDto) {
}
