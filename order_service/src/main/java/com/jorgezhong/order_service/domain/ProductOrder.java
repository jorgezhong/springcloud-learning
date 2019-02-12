package com.jorgezhong.order_service.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2018/11/13 14:37.
 */
public class ProductOrder implements Serializable {

    private int id;
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 订单流水号
     */
    private String tradeNo;
    /**
     * 价格，单位：分
     */
    private int price;
    private Date createTime;
    private int userId;
    private String username;


    public int getUserId() {
        return userId;
    }

    public ProductOrder setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProductOrder setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getId() {
        return id;
    }

    public ProductOrder setId(int id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ProductOrder setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public ProductOrder setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public ProductOrder setPrice(int price) {
        this.price = price;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductOrder setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
