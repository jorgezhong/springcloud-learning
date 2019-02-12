package com.jorgezhong.order_service.fallback;

import com.jorgezhong.order_service.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * Project <springcloud-learning>
 * 针对商品服务做降级处理
 *
 * @author jorgezhong
 * @date 2018/11/15 16:15
 */
@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String findById(int id) {
        System.out.println("feign 调用product-service findById 异常");
        return null;
    }
}
