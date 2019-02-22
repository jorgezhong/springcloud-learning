package com.jorge.orderservice.domain;

import java.util.Date;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/2/21 17:24.
 */
public class ProductOrder {

    private int id;
    private String productName;
    private int price;
    private String tradeNo;
    private Date createTime;
    private int userId;
    private String userName;

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

    public int getPrice() {
        return price;
    }

    public ProductOrder setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public ProductOrder setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductOrder setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public ProductOrder setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ProductOrder setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
