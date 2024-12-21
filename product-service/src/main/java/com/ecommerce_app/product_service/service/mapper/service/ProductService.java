package com.ecommerce_app.product_service.service.mapper.service;

import com.ecommerce_app.product_service.dto.req.ProductReq;
import com.ecommerce_app.product_service.dto.res.ProductRes;
import com.ecommerce_app.product_service.exception.ProductNotFoundException;
import com.ecommerce_app.product_service.model.Product;
import com.ecommerce_app.product_service.repository.ProductRepository;
import com.ecommerce_app.product_service.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;

    public ProductRes createProduct(ProductReq req){
        Product product = mapper.toEntity(req);
        return mapper.toRes(repo.save(product));
    }

    public ProductRes getProduct(String id){
        Product product = repo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with id: " + id + " not found"));
        return mapper.toRes(product);
    }
    public List<ProductRes> getAllProducts(){
        return repo.findAll().stream().map(mapper::toRes).toList();
    }

    public ProductRes updateProduct(String id, ProductReq req){
        Product updatedProduct = mapper.toEntity(req);
        repo.findById(id).ifPresentOrElse(product -> updatedProduct.setId(product.getId()),
                ()-> {throw new ProductNotFoundException("Product with id: " + id + " not found");});

        return mapper.toRes(repo.save(updatedProduct));
    }

    public void removeProduct(String id){
        Product product = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
        repo.delete(product);
    }

}
