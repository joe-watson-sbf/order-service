package com.joedev.orderservice.handler;

import com.joedev.orderservice.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerControllerException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDto> handleBusinessExceptionMessage(BusinessException e){
        var response = new ResponseDto(e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }

}
