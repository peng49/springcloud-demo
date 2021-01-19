package com.github.peng49.springclouddemo.controller;

import com.github.peng49.springclouddemo.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private DemoService demoService;

    @GetMapping("/test")
    public String test(@RequestParam(value = "ex", defaultValue = "false") boolean ex) {
        System.out.println(ex);
        return demoService.test(ex);
    }

    @GetMapping("/testForTimeout")
    public String testForTimeout() {
        return demoService.testTimeout();
    }
}
