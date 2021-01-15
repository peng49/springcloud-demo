package com.github.peng49.springclouddemo.service;

import com.github.peng49.springclouddemo.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@FeignClient("product-eureka-provider")
public interface ProductService {

    @RequestMapping("/product/list")
    List<Product> all();
}
