package com.github.peng49.springclouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class EurekaConsumerApplication {
    @Resource
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/consumer/{id}")
    public String consumerTest(@PathVariable("id") Integer id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://eureka-application-provider/provider/" + id, HttpMethod.GET, null, String.class);

        System.out.println(responseEntity.getBody());

        return responseEntity.getBody();
    }
}
