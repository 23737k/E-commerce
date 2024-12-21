package com.ecommerce_app.product_service.dto.req;

import com.ecommerce_app.product_service.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductReq(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "description is required")
        String description,
        @NotNull(message = "category is required")
        Category category,
        @NotNull(message = "price is required")
        @Positive(message = "price should be a positive value")
        BigDecimal price,
        @NotNull(message = "sku is required")
        String sku
) {
}
