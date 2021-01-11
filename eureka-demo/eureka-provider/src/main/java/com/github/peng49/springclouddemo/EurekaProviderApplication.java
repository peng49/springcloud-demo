package com.github.peng49.springclouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@SpringBootApplication
@RestController
public class EurekaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class, args);
    }

    @RequestMapping("/provider/{id}")
    public HashMap<String, String> providerMethod(@PathVariable("id") Integer id) {
        HashMap<String, String> map = new HashMap<>();
        map.put(id.toString(), "Provider");
        return map;
    }
}
