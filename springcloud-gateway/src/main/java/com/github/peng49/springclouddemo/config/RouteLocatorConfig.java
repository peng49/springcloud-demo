package com.github.peng49.springclouddemo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator A(RouteLocatorBuilder builder)
    {
        return builder.routes().route("A", predicateSpec -> predicateSpec
                .path("/test/**")
                .uri("http://localhost:9400")
        ).build();
    }
}
