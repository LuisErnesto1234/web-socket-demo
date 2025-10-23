package com.webnova.websocketsdemo.service;

import com.webnova.websocketsdemo.request.Product;
import com.webnova.websocketsdemo.request.ProductRequest;
import com.webnova.websocketsdemo.request.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private final AtomicLong count = new AtomicLong(1);
    private final ApplicationEventPublisher eventPublisher;

    public ProductResponse create(ProductRequest productRequest) {
        Product product = Product.builder()
                .id(count.getAndIncrement())
                .nombre(productRequest.getNombre())
                .price(productRequest.getPrice())
                .build();

        productList.add(product);

        eventPublisher.publishEvent(new ProductResponse(product.getId(), product.getNombre(), product.getPrice()));

        return new ProductResponse(product.getId(), product.getNombre(), product.getPrice());
    }

    public void update(ProductRequest productRequest, Long id) {
        Product productFinded = productList.stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El producto no existe con dicho id"));

        productFinded.setPrice(productRequest.getPrice());
        productFinded.setNombre(productRequest.getNombre());
    }

    public void delete(Long id) {
        productList.removeIf(p -> p.getId().equals(id));
    }

    public List<ProductResponse> findAll() {
        return productList.stream()
                .map(p -> new ProductResponse(p.getId(), p.getNombre(), p.getPrice())).toList();
    }
}
