package com.example.online_auction_platform.services.product;

import java.util.List;
import com.example.online_auction_platform.entities.Product;

public interface ProductService {
    
    List<Product> getProductByAuctioneerId(int auctioneerId);
    
    List<Product> getAllProducts();

    Product findById(int productId);

    Product save(Product product);

    int getHighestBiddenPriceByProductId(int productId);

}
