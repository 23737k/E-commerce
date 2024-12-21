package com.ecommerce_app.product_service.controller;

import com.ecommerce_app.product_service.dto.req.ProductReq;
import com.ecommerce_app.product_service.dto.res.ProductRes;
import com.ecommerce_app.product_service.service.mapper.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductRes> getProductById(@PathVariable String id){
        return ResponseEntity.ok(service.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductRes>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductRes> createProduct(@RequestBody @Validated ProductReq productReq){
        return ResponseEntity.ok(service.createProduct(productReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRes> updateProduct(@PathVariable String id, @RequestBody @Validated ProductReq productReq){
        return new ResponseEntity<>(service.updateProduct(id, productReq), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable String id){
        service.removeProduct(id);
        return ResponseEntity.accepted().build();
    }
}
