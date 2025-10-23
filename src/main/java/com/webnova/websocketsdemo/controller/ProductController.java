package com.webnova.websocketsdemo.controller;

import com.webnova.websocketsdemo.request.ProductRequest;
import com.webnova.websocketsdemo.request.ProductResponse;
import com.webnova.websocketsdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.findAll());
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProductRequest productRequest, @RequestParam Long id){
        productService.update(productRequest, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.create(productRequest));
    }
}
