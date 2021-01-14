package com.github.peng49.springclouddemo.service;

import com.github.peng49.springclouddemo.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public List<Product> all() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "手机", 1000.11));
        products.add(new Product(2, "电脑", 3000.11));
        return products;
    }
}
