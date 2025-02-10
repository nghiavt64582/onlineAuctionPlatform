package com.example.online_auction_platform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.entities.Product;
import com.example.online_auction_platform.services.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductService productService;
    Gson gson = new GsonBuilder()
        .create();

    @GetMapping("/products/id")
    public Product getProduct(@RequestParam Integer productId) {
        System.out.println("ProductController.getProduct");
        Product result = productService.findById(productId);
        // System.out.println("ProductController.getProduct result: " + gson.toJson(result));
        return result;
    }

    @GetMapping("/test")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        System.out.println("ProductController.getProducts");
        return productService.getAllProducts();
    }

    @GetMapping("/products/imageUrl")
    public Product findByImageUrl(@RequestParam String imageUrl) {
        return productService.findByImageUrl(imageUrl);
    }
    
}
