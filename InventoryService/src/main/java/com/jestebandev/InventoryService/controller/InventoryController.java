package com.jestebandev.InventoryService.controller;

import com.jestebandev.InventoryService.dto.Response;
import com.jestebandev.InventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response isInStock(@RequestParam List<String> skuCode){
        return new Response(Instant.now(), HttpStatus.OK.value(),HttpStatus.OK,
                "Check inventory", Map.of("inventory",inventoryService.isInStock(skuCode)));
    }
}
