package com.ecommerce_app.product_service.service.mapper;

import com.ecommerce_app.product_service.dto.req.ProductReq;
import com.ecommerce_app.product_service.dto.res.ProductRes;
import com.ecommerce_app.product_service.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product toEntity(ProductReq req);
    ProductRes toRes(Product product);
}
