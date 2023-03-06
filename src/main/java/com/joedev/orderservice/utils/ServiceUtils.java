package com.joedev.orderservice.utils;

import com.joedev.orderservice.dto.ClientDto;
import com.joedev.orderservice.dto.OrderCommand;
import com.joedev.orderservice.handler.BusinessException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceUtils {

    private ServiceUtils(){}

    public static void requiredNonNullObject(Object object){
        if(object==null){
            throw new BusinessException("Required non null object!", HttpStatus.FORBIDDEN);
        }
    }

    public static void requiredNonNonEmptyList(List<?> list){
        if(list==null || list.isEmpty()){
            throw new BusinessException("Required non empty list data!", HttpStatus.FORBIDDEN);
        }
    }

    private static boolean isEmptyString(String str){
        if(str==null){
            return true;
        }
        return str.isEmpty() || str.isBlank();
    }

    public static void validateClientData(ClientDto clientDto){
        verifyClient(clientDto);
        if (isEmptyString(clientDto.getName())){
            throw new BusinessException("The client name is required!", HttpStatus.NOT_ACCEPTABLE);
        }

        if(isEmptyString(clientDto.getAddress())){
            throw new BusinessException("The client address is required!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private static void verifyClient(ClientDto clientDto){
        if(clientDto==null){
            throw new BusinessException("The client data is required!", HttpStatus.NO_CONTENT);
        }
    }


    public static void validateOrder(OrderCommand command){
        requiredNonNullObject(command);
        requiredNonNullObject(command.clientDto());
        requiredNonNonEmptyList(command.orderDetailsDto());
        validateClientData(command.clientDto());
    }
}
