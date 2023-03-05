package com.jestebandev.ProductService.dto;

import java.math.BigDecimal;
public record ProductRequest(String name,String description,BigDecimal price) {
}
