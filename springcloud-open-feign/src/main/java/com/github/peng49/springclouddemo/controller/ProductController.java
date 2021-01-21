package com.github.peng49.springclouddemo.controller;

import com.github.peng49.springclouddemo.pojo.Product;
import com.github.peng49.springclouddemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> list(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        HashMap<String, String> headers = new HashMap<>();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            headers.put(name,request.getHeader(name));
        }

        log.info("Headers: {}", headers);
        return productService.all();
    }
}
