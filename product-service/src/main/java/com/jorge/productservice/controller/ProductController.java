package com.jorge.productservice.controller;

import com.jorge.productservice.domain.Product;
import com.jorge.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/product")
@RefreshScope
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Value("${server.port}")
    private String port;

    @Value("${branch}")
    private String branch;

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public Object list() {
        return productService.listProduct();
    }


    @RequestMapping("/find")
    public Object findById(@RequestParam("id") int id) {

        LOGGER.info("count info");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            //ignore
        }

        Product product = productService.findById(id);

        Product result = new Product();

        BeanUtils.copyProperties(product,result);

        result.setName(result.getName() + " data from port=" + port + ", branch=" + branch);

        return result;
    }
}