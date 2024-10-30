package com.example.onlineAuctionPlatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineAuctionPlatform.entities.Auctioneer;
import com.example.onlineAuctionPlatform.entities.Product;
import com.example.onlineAuctionPlatform.helpers.ProductHelper;
import com.example.onlineAuctionPlatform.services.auctioneer.AuctioneerService;
import com.example.onlineAuctionPlatform.services.product.ProductService;
import java.util.List;

@RestController
@RequestMapping("/auctioneer")
public class AuctioneerController {

    private AuctioneerService auctioneerService;
    private ProductService productService;
    
    public AuctioneerController(
        AuctioneerService auctioneerService, 
        ProductService productService
    ) {
        this.auctioneerService = auctioneerService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Auctioneer> getAuctioneers() {
        return auctioneerService.getAllAutioneers();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        ProductHelper.updateInfoPostedProduct(product);
        Product dbProduct = productService.save(product);
        return dbProduct;
    }

    @GetMapping("/productsByAuctioneerId/{auctioneerId}")
    public List<Product> getProductsByAuctioneerId(@PathVariable int auctioneerId) {
        return productService.getProductByAuctioneerId(auctioneerId);
    }
}
