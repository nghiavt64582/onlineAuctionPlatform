package com.example.onlineAuctionPlatform.services.product;

import java.util.List;
import com.example.onlineAuctionPlatform.entities.Product;

public interface ProductService {
    
    List<Product> getProductByAuctioneerId(int auctioneerId);
    
    List<Product> getAllProducts();

    Product getProductById(int productId);

    Product save(Product product);

    int getHighestBiddenPriceByProductId(int productId);

}
