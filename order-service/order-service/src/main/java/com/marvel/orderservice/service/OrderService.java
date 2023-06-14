package com.marvel.orderservice.service;

import com.marvel.orderservice.Event.OrderPlacedEvent;
import com.marvel.orderservice.Repository.OrderRepository;

import com.marvel.orderservice.dto.InventoryResponse;
import com.marvel.orderservice.dto.OrderLineItemsDto;
import com.marvel.orderservice.dto.OrderRequest;
import com.marvel.orderservice.model.Order;
import com.marvel.orderservice.model.OrderLineItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private KafkaTemplate<String , OrderPlacedEvent> kafkaTemplate;

    public String orderPlaced(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

           order.setOrderLineItemsList(orderLineItems);

           List<String> skuCodes =order.getOrderLineItemsList().stream()
                   .map(OrderLineItems::getSkuCode)
                   .collect(Collectors.toList());
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://Inventry-service/Api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);
        if (allProductsInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("notificationtopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order Placed successfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }


    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }
}
