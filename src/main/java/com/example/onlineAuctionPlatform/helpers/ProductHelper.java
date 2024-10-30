package com.example.onlineAuctionPlatform.helpers;

import com.example.onlineAuctionPlatform.entities.Product;

public class ProductHelper {
    
    public static void updateInfoPostedProduct(Product product) {
        product.setPostedDate();
        product.setCurrentPrice(product.getBeginningPrice());
    }

}
