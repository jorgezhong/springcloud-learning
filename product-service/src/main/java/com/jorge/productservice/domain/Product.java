package com.jorge.productservice.domain;

import java.io.Serializable;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2018/11/1 14:23.
 */
public class Product implements Serializable {


    private int id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格：分为单位
     */
    private int price;

    /**
     * 库存
     */
    private int store;


    public Product() {
    }

    public Product(int id, String name, int price, int store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Product setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getStore() {
        return store;
    }

    public Product setStore(int store) {
        this.store = store;
        return this;
    }
}