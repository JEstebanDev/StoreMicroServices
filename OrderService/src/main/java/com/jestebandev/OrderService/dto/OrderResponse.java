package com.jestebandev.OrderService.dto;

import java.util.List;

public record OrderResponse(String orderNumber, List<ItemOrderDto> ItemOrderListDTO) {
}
