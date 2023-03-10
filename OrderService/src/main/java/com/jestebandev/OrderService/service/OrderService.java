package com.jestebandev.OrderService.service;

import com.google.gson.Gson;
import com.jestebandev.OrderService.dto.*;
import com.jestebandev.OrderService.error.CustomErrorException;
import com.jestebandev.OrderService.event.OrderPlacedEvent;
import com.jestebandev.OrderService.model.Order;
import com.jestebandev.OrderService.model.ItemOrder;
import com.jestebandev.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<ItemOrder> orderLineItems = orderRequest.ItemOrderDto().stream().map(this::mapToObject).toList();
        order.setItemOrderList(orderLineItems);

        boolean isInventoryValid = checkInventory(order.getItemOrderList());
        if (isInventoryValid) {
            orderRepository.save(order);
            //kafka message
            kafkaTemplate.send("NotificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
            return new OrderResponse(order.getOrderNumber(), orderRequest.ItemOrderDto());
        }
        throw new CustomErrorException("ERR-001", "Something went wrong with the inventory validation check the products");
    }

    private boolean checkInventory(List<ItemOrder> itemOrderList) {
        Response responseInventory = getDataWebClient(itemOrderList);
        //transform the json to object with the gson library
        Gson gson = new Gson();
        Inventory inventory = gson.fromJson(responseInventory.data().toString(), Inventory.class);
        List<InventoryResponse> listInventory = inventory.getInventory();

        if (listInventory.size() == itemOrderList.size()) {
            return listInventory.stream().allMatch(InventoryResponse::isInStock);
        }
        throw new CustomErrorException("ERR-002", "Check if the exist or check the skuCode");
    }

    private Response getDataWebClient(List<ItemOrder> itemOrderList) {
        List<String> skuCodeList = itemOrderList.stream().map(ItemOrder::getSkuCode).toList();
        return webClientBuilder.build().get().uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve().bodyToMono(Response.class).block();
    }

    private ItemOrder mapToObject(ItemOrderDto ItemOrderDto) {
        ItemOrder orderLineItem = new ItemOrder();
        orderLineItem.setSkuCode(ItemOrderDto.skuCode());
        orderLineItem.setQuantity(ItemOrderDto.quantity());
        orderLineItem.setPrice(ItemOrderDto.price());
        return orderLineItem;
    }
}
