package com.github.peng49.springclouddemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.peng49.springclouddemo.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Resource
    private RestTemplate restTemplate;

    public List<Product> all() throws JsonProcessingException {
        ResponseEntity<String> entity = restTemplate.exchange("http://nacos-provider/product/list", HttpMethod.GET, null, String.class);
        String body = entity.getBody();

        log.info(body);

        ObjectMapper mapper = new ObjectMapper();

        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Product.class);

        return mapper.readValue(body == null ? "{}" : body, javaType);
    }
}
