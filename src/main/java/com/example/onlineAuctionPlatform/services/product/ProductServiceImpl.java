package com.example.onlineAuctionPlatform.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.onlineAuctionPlatform.entities.Product;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getProductByAuctioneerId(int auctioneerId) {
        return productRepo.findAll().stream().filter(product -> {
            return product.getAuctioneerId() == auctioneerId;
        }).toList();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        Optional<Product> result = productRepo.findById(productId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }
    
}
