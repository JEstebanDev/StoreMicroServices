package com.jestebandev.OrderService.controller;

import com.jestebandev.OrderService.dto.OrderRequest;
import com.jestebandev.OrderService.dto.Response;
import com.jestebandev.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response placeOrder(@RequestBody OrderRequest orderRequest) {
        return new Response(Instant.now(), HttpStatus.CREATED.value(),HttpStatus.CREATED,
                "Created order", Map.of("order",orderService.placeOrder(orderRequest)));
    }
}
