package com.jorge.orderservice.controller;

import com.jorge.orderservice.domain.ProductOrder;
import com.jorge.orderservice.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {


    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId, HttpServletRequest request) {

        LOGGER.info("test info");
        LOGGER.debug("test info");

        String token = request.getHeader("token");
        String cookie = request.getHeader("cookie");
        System.out.println("token = " + token);
        System.out.println("cookie = " + cookie);

        ProductOrder productOrder = productOrderService.save(userId, productId);

        Map<String, Object> result = new HashMap<>(16);
        result.put("code", 0);
        result.put("data", productOrder);

        return result;
    }


    /**
     * 注意方法签名要一致
     *
     * @param userId
     * @param productId
     * @return
     */
    private Object saveOrderFail(int userId, int productId, HttpServletRequest request) {

        //监控告警,异步发送短信
        String saveOrderKey = "save-order";
        String sendValue = redisTemplate.opsForValue().get(saveOrderKey);
        final String ip = request.getRemoteAddr();

        CompletableFuture.runAsync(() -> {
            if (StringUtils.isBlank(sendValue)) {
                System.out.println("紧急短信，用户下单失败，请立刻查找原因，ip：" + ip);
                //todo 发送一个http请求，调用短信服务
                redisTemplate.opsForValue().set(saveOrderKey, "save-order-fail", 20, TimeUnit.SECONDS);

            } else {
                System.out.println("已经发送过短信，20s内不重复发送");
            }

        }, Executors.newFixedThreadPool(2));


        Map<String, Object> msg = new HashMap<>(16);
        msg.put("code", -1);
        msg.put("msg", "您被挤出来了，稍后重试");
        return msg;
    }

}