package com.jestebandev.InventoryService.service;

import com.jestebandev.InventoryService.dto.InventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IInventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
