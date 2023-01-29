package com.joedev.orderservice.utils;

import com.joedev.orderservice.dto.ClientDto;
import com.joedev.orderservice.dto.OrderCommand;
import com.joedev.orderservice.handler.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static com.joedev.orderservice.utils.ServiceUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUtilsTest {

    @Test
    @DisplayName("REQUIRED NON NULL OBJECT")
    void requiredNonNullObjectTest() {
        try{
            requiredNonNullObject(null);
        }catch (BusinessException exception){
            assertEquals("Required non null object!", exception.getMessage());
        }
    }

    @Test
    @DisplayName("REQUIRED NON NULL AND EMPTY LIST")
    void requiredNonNonEmptyListTest() {
        try{
            requiredNonNonEmptyList(List.of());
        }catch (BusinessException exception){
            assertEquals("Required non empty list data!", exception.getMessage());
        }
    }

    @Test
    void validateClientDataTest() {
        ClientDto clientDto = new ClientDto();
        try{
            validateClientData(clientDto);
        }catch (BusinessException ex){
            assertEquals("The client name is required!", ex.getMessage());
        }
    }

    @Test
    void validateOrderTest() {
        OrderCommand command = new OrderCommand(new ClientDto(), List.of());
        try{
            validateOrder(command);
        }catch (BusinessException ex){
            assertEquals("Required non empty list data!", ex.getMessage());
        }
    }
}