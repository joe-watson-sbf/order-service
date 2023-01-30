package com.joedev.orderservice.controller;

import com.joedev.orderservice.OrderServiceApplication;
import com.joedev.orderservice.dto.*;
import com.joedev.orderservice.service.order.OrderService;
import com.joedev.orderservice.service.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = OrderServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration.properties")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;
    private final String url = "http://localhost:8080/api/orders";
    @Test
    @DisplayName("GET ALL ORDERS")
    void getOrders() throws Exception {

        List<OrderDto> orderDto = fakeOrders();
        when(service.getAllOrders()).thenReturn(orderDto);

        long clientId = 9L;
        this.mockMvc.perform(get(url))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].id").value(clientId));
    }


    @Test
    @DisplayName("GET ORDER BY ID")
    void getOrder() throws Exception {

        List<OrderDto> orderDto = fakeOrders();
        OrderCommand command = new OrderCommand(
                new ClientDto(
                        1L,
                        "JOE WATSON SBF",
                        "Medellin, Colombia"),
                List.of(
                        new OrderDetailsDto(
                                new ProductDto(232L, "Iphone 14",
                                        new BigDecimal(345),
                                        45, true), 5)
                        )

                );

        // when(service.createOrder(command)).thenReturn(command);

        long actual = 1L;

        this.mockMvc.perform(get(url+"/1"))
                .andDo(print())
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value(actual));
    }

    @Test
    void placeOrder() {
    }


    private List<OrderDto> fakeOrders(){
        return List.of(
                new OrderDto(6L, 800L, List.of(), Instant.now(), new BigDecimal(5454)),
                new OrderDto(9L, 700L, List.of(), Instant.now(), new BigDecimal(654)),
                new OrderDto(4L, 300L, List.of(), Instant.now(), new BigDecimal(900))
        );
    }
}