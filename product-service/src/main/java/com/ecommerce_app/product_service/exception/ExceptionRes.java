package com.ecommerce_app.product_service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionRes<T>(
        Integer status,
        String message,
        String description,
        T data
) {
}