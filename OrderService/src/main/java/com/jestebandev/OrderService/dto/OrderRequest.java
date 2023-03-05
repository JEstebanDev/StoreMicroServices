package com.jestebandev.OrderService.dto;

import java.util.List;

public record OrderRequest(List<ItemOrderDto> ItemOrderDto) {
}
