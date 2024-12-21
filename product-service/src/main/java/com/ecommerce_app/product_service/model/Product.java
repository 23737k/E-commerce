package com.ecommerce_app.product_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    private String sku;
}
