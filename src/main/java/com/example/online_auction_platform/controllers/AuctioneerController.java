package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.entities.Auctioneer;
import com.example.online_auction_platform.entities.Product;
import com.example.online_auction_platform.helpers.ProductHelper;
import com.example.online_auction_platform.services.AuctioneerService;
import com.example.online_auction_platform.services.ProductService;
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

    @PutMapping("/auctioneer")
    public void update(@RequestBody Auctioneer auctioneer) {
        Auctioneer dbAuctioneer = auctioneerService.findById(auctioneer.getId());
        if (dbAuctioneer == null) {
            throw new RuntimeException("No valid bidder with id : " + auctioneer.getId());
        }
        dbAuctioneer.setUsername(auctioneer.getUsername());
        dbAuctioneer.setEmail(auctioneer.getEmail());
        dbAuctioneer.updateCreatedDate();

        auctioneerService.save(dbAuctioneer);
    }

    @GetMapping("/products")
    public List<Product> getAuctioneers() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public Product findById(@PathVariable int productId) {
        return productService.findById(productId);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        ProductHelper.updateInfoPostedProduct(product);
        Product dbProduct = productService.save(product);
        return dbProduct;
    }

    @GetMapping("/productsByAuctioneerId/{auctioneerId}")
    public List<Product> findProductsByAuctioneerId(@PathVariable int auctioneerId) {
        return productService.findProductByAuctioneerId(auctioneerId);
    }
}
