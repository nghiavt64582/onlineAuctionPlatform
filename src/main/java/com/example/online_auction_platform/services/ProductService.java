package com.example.online_auction_platform.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.online_auction_platform.dto.request.product.AddProductReqDto;
import com.example.online_auction_platform.entities.Product;
import com.example.online_auction_platform.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepo;
    private ImageService imageService;

    public List<Product> findProductByAuctioneerId(int auctioneerId) {
        return productRepo.findByAuctioneer_Id(auctioneerId, PageRequest.of(0, 20)).getContent();
    }

    public List<Product> getAllProducts() {
        System.out.println("ProductServiceImpl.getAllProducts");
        return productRepo.findAll();
    }

    public Product findById(int productId) {
        Optional<Product> result = productRepo.findById(productId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public int findHighestBiddenPriceByProductId(int productId) {
        // todo
        return 0;
    }

    public Product findByImageUrl(String imageUrl) {
        Optional<Product> result = productRepo.findByImageUrl(imageUrl);
        return result.get();
    }

    public Product addNewProduct(AddProductReqDto addProductDto) {
        
        // 1. Save image
        String imagePath;
        try {
            imagePath = imageService.addImage(addProductDto.getImage());
        } catch (Exception e) {
            throw new RuntimeException("Image is null.");
        }

        // 2. Process product data
        Product product = Product.builder()
            .name(addProductDto.getName())
            .location(addProductDto.getLocation())
            .beginningPrice(addProductDto.getPrice())
            .currentPrice(addProductDto.getPrice())
            .imageUrl(imagePath).build();

        save(product);
        return product;
    }

    public List<Product> findByAuctioneerId(int auctioneerId, Pageable pageable) {
        // combine with redis aspect
        List<Product> result = productRepo.findByAuctioneer_Id(auctioneerId, pageable).getContent();
        result.forEach(product -> product.setBiddenPrices(List.of()));
        return result;
    }
}
