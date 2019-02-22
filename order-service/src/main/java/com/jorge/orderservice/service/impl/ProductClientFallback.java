package com.jorge.orderservice.service.impl;

import com.jorge.orderservice.service.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String findById(int id) {
        System.out.println("feign 调用product-service findById 异常");
        return null;
    }
}