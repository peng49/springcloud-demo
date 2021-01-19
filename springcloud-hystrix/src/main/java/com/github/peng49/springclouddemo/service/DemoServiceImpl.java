package com.github.peng49.springclouddemo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),  //熔断时间5秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")  //错误流程比例
    }, fallbackMethod = "testFallback")
    @Override
    public String test(boolean ex) {
        System.out.println(DemoServiceImpl.class.getName());
        if (ex) {
            throw new RuntimeException(RuntimeException.class.getName());
        }
        return DemoServiceImpl.class.getName();
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
    }, fallbackMethod = "testFallback")
    public String testTimeout() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DemoServiceImpl.class.getName());

        return DemoServiceImpl.class.getName();
    }

    public String testFallback()
    {
        return "testFallback222222";
    }

    public String testFallback(boolean ex) {
        return "testFallback";
    }
}
