package com.jestebandev.OrderService.dto;

import java.math.BigDecimal;

public record ItemOrderDto(String skuCode, BigDecimal price, Integer quantity) {
}
