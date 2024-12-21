package com.ecommerce_app.product_service.dto.res;

import com.ecommerce_app.product_service.model.Category;

import java.math.BigDecimal;

public record ProductRes(
        String id,
        String name,
        String description,
        Category category,
        BigDecimal price,
        String sku
) {
}
