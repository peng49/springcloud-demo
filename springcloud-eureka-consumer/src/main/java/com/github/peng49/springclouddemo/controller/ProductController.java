package com.github.peng49.springclouddemo.controller;

import com.github.peng49.springclouddemo.pojo.Product;
import com.github.peng49.springclouddemo.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    public List<Product> listForAnnotation()
    {
        return null;
    }

    public List<Product> listForRestTemplate()
    {
        return null;
    }

    public List<Product> listFor()
    {
        return null;
    }
}
