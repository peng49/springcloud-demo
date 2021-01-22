package com.github.peng49.springclouddemo.controller;

import com.github.peng49.springclouddemo.pojo.Product;
import com.github.peng49.springclouddemo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> list() {
        return productService.all();
    }


    @GetMapping("/{id}")
    public Product get(@PathVariable("id") Integer id) {
        return productService.get(id);
    }
}
