package com.jorgezhong.order_service.service;

import com.jorgezhong.order_service.domain.ProductOrder;

/**
 * Project <springcloud-learning>
 *  订单业务类
 * @author jorgezhong
 * @date 2018/11/13 14:42
 */
public interface ProductOrderService {


    /**
     * 下单接口
     * @param userId
     * @param productId
     * @return
     */
    ProductOrder save(int userId,int productId);

}
