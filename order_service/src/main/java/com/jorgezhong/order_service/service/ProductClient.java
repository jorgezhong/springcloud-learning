package com.jorgezhong.order_service.service;

import com.jorgezhong.order_service.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Project <springcloud-learning>
 *  商品服务客户端
 *
 * @author jorgezhong
 * @date 2018/11/15 10:01
 */
@FeignClient(name = "product-service",fallback = ProductClientFallback.class)
public interface ProductClient {


    @GetMapping("/api/vi/product/find")
    String findById(@RequestParam(value = "id") int id);

}
