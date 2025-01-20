package com.example.online_auction_platform.helpers;

import com.example.online_auction_platform.entities.Product;

public class ProductHelper {
    
    public static void updateInfoPostedProduct(Product product) {
        product.setPostedDate();
        product.setCurrentPrice(product.getBeginningPrice());
    }

}
