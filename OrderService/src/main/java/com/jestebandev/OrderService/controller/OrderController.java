package com.jestebandev.OrderService.controller;

import com.jestebandev.OrderService.dto.OrderRequest;
import com.jestebandev.OrderService.dto.Response;
import com.jestebandev.OrderService.error.CustomErrorException;
import com.jestebandev.OrderService.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory" , fallbackMethod = "fallbackMethod") //Apply the circuit breaker logic
    @TimeLimiter(name="inventory")
    @Retry(name = "inventory")
    public CompletableFuture<Response> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(()->new Response(Instant.now(), HttpStatus.CREATED.value(),HttpStatus.CREATED,
                "Created order", Map.of("order",orderService.placeOrder(orderRequest))));
    }
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public CompletableFuture<Response> fallbackMethod(RuntimeException runtimeException){
        // The error for circuit breaker is 429 or TOO_MANY_REQUESTS
        return CompletableFuture.supplyAsync(()->new Response(Instant.now(), HttpStatus.TOO_MANY_REQUESTS.value(),HttpStatus.TOO_MANY_REQUESTS,
                "Error Created order", Map.of("Error Info","Oops! Something went wrong, please try again later!")));
    }
}
