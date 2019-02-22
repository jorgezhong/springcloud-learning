package com.jorge.orderservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.jorge.orderservice.domain.ProductOrder;
import com.jorge.orderservice.service.ProductClient;
import com.jorge.orderservice.service.ProductOrderService;
import com.jorge.orderservice.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

/**
 * Project <springcloud-learning>
 *
 * @author jorgezhong
 * @date 2018/11/13 14:46
 */
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    //ribbon方式一
    private RestTemplate restTemplate;

    @Autowired
    //ribbon方式二
    private LoadBalancerClient loadBalancerClient;

    //feign方式
    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {

/*
        //获取商品详情,url指定应用名称而不是ip
        //方式一：使用LoadBalanced注解,包装的restTemplate
       Map<String, Object> productMap = restTemplate.getForObject("http://product-service/api/vi/product/find?id=" + productId, Map.class);
    

        //方式二：直接使用loadBalancerClient做负载均衡层
        ServiceInstance instance = loadBalancerClient.choose("product-service");

        String url = String.format("http://%s:%s/api/vi/product/find?id=" + productId, instance.getHost(), instance.getPort());
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> productMap = restTemplate.getForObject(url, Map.class);
*/

        //feign方式
        String response = productClient.findById(productId);
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);

        ProductOrder productOrder = new ProductOrder()
                .setCreateTime(new Date())
                .setUserId(userId)
                .setProductName(jsonNode.get("name").toString())
                .setPrice(Integer.parseInt(jsonNode.get("price").toString()))
                .setTradeNo(UUID.randomUUID().toString());
        return productOrder;
    }
}