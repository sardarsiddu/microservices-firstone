package com.marvel.inventoryservice.controller;

import com.marvel.inventoryservice.dto.InventoryResponse;
import com.marvel.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/inventory")
@Slf4j
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isINStock(skuCode) ;
    }
}
