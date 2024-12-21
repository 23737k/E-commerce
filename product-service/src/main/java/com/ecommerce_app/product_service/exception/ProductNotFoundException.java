package com.ecommerce_app.product_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductNotFoundException extends RuntimeException{
    private String msg;
}
