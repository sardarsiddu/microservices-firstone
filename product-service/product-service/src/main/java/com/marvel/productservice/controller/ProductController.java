package com.marvel.productservice.controller;

import com.marvel.productservice.dto.ProductRequest;
import com.marvel.productservice.dto.ProductResponse;
import com.marvel.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/product")

public class ProductController {
     @Autowired
    private ProductService productService;

     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public void creatproduct(@RequestBody ProductRequest productRequest){
         productService.creatProduct(productRequest);
     }

     @GetMapping
     @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllsavedProducts(){
       return   productService.getAllproducts();
    }
}
