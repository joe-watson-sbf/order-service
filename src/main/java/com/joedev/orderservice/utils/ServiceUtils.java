package com.joedev.orderservice.utils;

import com.joedev.orderservice.handler.BusinessException;
import org.springframework.http.HttpStatus;

public class ServiceUtils {

    public static void requiredNonNullObject(Object object){
        if(object==null){
            throw new BusinessException("Required non null object!", HttpStatus.FORBIDDEN);
        }
    }
}
