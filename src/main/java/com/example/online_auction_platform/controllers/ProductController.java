package com.example.online_auction_platform.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.dto.request.product.GetSellingProductReqDto;
import com.example.online_auction_platform.dto.request.product.AddProductReqDto;
import com.example.online_auction_platform.entities.Product;
import com.example.online_auction_platform.services.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    Gson gson = new GsonBuilder().create();

    @GetMapping("/selling")
    public List<Product> getSellingProduct(
        @RequestBody GetSellingProductReqDto requestData
    ) {
        System.out.println("ProductController.getProduct");
        List<Product> result = productService.findByAuctioneerId(
            requestData.getAuctioneerId(), 
            PageRequest.of(0, 5)
        );
        // System.out.println("ProductController.getProduct result: " + gson.toJson(result));
        return result;
    }

    @GetMapping("/open")
    public List<Product> getOpenProducts() {
        System.out.println("ProductController.getProducts");
        return productService.getAllProducts();
    }

    @PostMapping("")
    public ResponseEntity<?> addNewProduct(@RequestBody AddProductReqDto addProductDto) throws IOException {
        productService.addNewProduct(addProductDto);
        return ResponseEntity.ok("Product created successfully!");
    }

    @GetMapping("bidding")
    public List<Product> getBiddingProduct() {
        return new ArrayList<>();
    }
}
