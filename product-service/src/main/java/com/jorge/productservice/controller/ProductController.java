package com.jorge.productservice.controller;

import com.jorge.productservice.domain.Product;
import com.jorge.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public Object list() {
        return productService.listProduct();
    }


    @RequestMapping("/find")
    public Object findById(@RequestParam("id") int id) {

        LOGGER.info("test info");

        Product product = productService.findById(id);

        Product result = new Product();

        BeanUtils.copyProperties(product,result);

        result.setName(result.getName() + " data from port=" + port);

        return result;
    }
}