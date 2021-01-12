package com.github.peng49.springclouddemo.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient("eureka-application-provider")
public interface TestService {

    @RequestMapping("/provider/{id}")
    Map<String,String> providerMethod(@PathVariable("id") Integer id);
}
