package com.jestebandev.InventoryService.service;

import com.jestebandev.InventoryService.dto.InventoryResponse;
import com.jestebandev.InventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0)
                ).toList();
    }
}
