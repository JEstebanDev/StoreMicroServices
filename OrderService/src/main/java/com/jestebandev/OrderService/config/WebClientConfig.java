package com.jestebandev.OrderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // To use WebClient import the dependency webflux in the pom file
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
