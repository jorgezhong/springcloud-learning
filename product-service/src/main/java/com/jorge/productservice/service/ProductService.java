package com.jorge.productservice.service;

import com.jorge.productservice.domain.Product;

import java.util.List;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2018/11/1 14:25.
 */

public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);
}
