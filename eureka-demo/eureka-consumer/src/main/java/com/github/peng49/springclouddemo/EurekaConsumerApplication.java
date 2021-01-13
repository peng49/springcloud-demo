package com.github.peng49.springclouddemo;

import com.github.peng49.springclouddemo.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
@EnableFeignClients
public class EurekaConsumerApplication {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private TestService testService;

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

    @RequestMapping("/consumerForFeign/{id}")
    public Map<String, String> consumerForFeignTest(@PathVariable("id") Integer id) {
        return testService.providerMethod(id);
    }

    @Value("${env}")
    private String env;

    @RequestMapping("/config")
    public String configTest()
    {
        return this.env;
    }
}
