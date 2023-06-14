package com.marvel.inventoryservice.service;

import com.marvel.inventoryservice.Repository.InventoryRepository;
import com.marvel.inventoryservice.dto.InventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryService {

    @Autowired
   private InventoryRepository inventoryRepository;

    public List<InventoryResponse>isINStock(List<String> skuCode){
  log.info("cheking the inventory");
       return inventoryRepository.findBySkuCodeIn(skuCode).stream()
               .map(inventory -> InventoryResponse.builder()
                       .skuCode(inventory.getSkuCode())
                       .isInStock(inventory.getQuantity() >0 )
                       .build()
               ).collect(Collectors.toList());
    }
}
