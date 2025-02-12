package com.example.online_auction_platform.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.dto.request.product.PostProductDto;
import com.example.online_auction_platform.entities.Product;
import com.example.online_auction_platform.services.ImageService;
import com.example.online_auction_platform.services.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @Autowired
    ImageService imageService;
    
    Gson gson = new GsonBuilder().create();

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

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody PostProductDto postProductDto) throws IOException {
        // 1. Save image
        String imagePath = imageService.addImage(postProductDto.getImage());
        if (imagePath == null) {
            throw new RuntimeException("");
        }

        // 2. Process product data
        Product product = Product.builder()
            .name(postProductDto.getName())
            .location(postProductDto.getLocation())
            .beginningPrice(postProductDto.getPrice())
            .currentPrice(postProductDto.getPrice())
            .imageUrl(imagePath).build();

        productService.save(product);

        return ResponseEntity.ok("Product created successfully!");
    }
}
