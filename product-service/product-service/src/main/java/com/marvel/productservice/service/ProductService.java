package com.marvel.productservice.service;

import com.marvel.productservice.dto.ProductRequest;
import com.marvel.productservice.dto.ProductResponse;
import com.marvel.productservice.model.Product;
import com.marvel.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class ProductService {

    @Autowired
    private  ProductRepository productRepository;





   public void creatProduct(ProductRequest productRequest){
       Product product = Product.builder()

               .name(productRequest.getName())
               .description(productRequest.getDescription())
               .price(productRequest.getPrice())
               .build();
       productRepository.save(product);


   }

   public List<ProductResponse> getAllproducts(){
List<Product> products = productRepository.findAll();

       return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
   }

    private ProductResponse  mapToProductResponse(Product product) {
           return  ProductResponse.builder()
                   .id(product.getId())
                   .name(product.getName())
                   .description(product.getDescription())
                   .price(product.getPrice())
                   .build();
    }


}
