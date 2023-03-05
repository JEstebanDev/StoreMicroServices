package com.jestebandev.OrderService.dto;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;
public record Response(
        Instant timeStamp,
        int statusCode,
        HttpStatus status,
        String message,
        Map<?, ?> data){}
