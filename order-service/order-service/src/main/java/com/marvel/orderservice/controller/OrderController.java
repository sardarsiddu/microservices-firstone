package com.marvel.orderservice.controller;

import com.marvel.orderservice.dto.OrderRequest;
import com.marvel.orderservice.model.Order;
import com.marvel.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/Api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    public CompletableFuture<String> placedOrder(@RequestBody OrderRequest orderRequest){
       return CompletableFuture.supplyAsync(() ->orderService.orderPlaced(orderRequest));

    }
    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return  CompletableFuture.supplyAsync(() ->"opp! somthing went wrong, please try again later");
    }
}
