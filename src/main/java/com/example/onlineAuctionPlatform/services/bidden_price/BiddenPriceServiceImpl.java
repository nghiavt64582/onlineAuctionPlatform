package com.example.onlineAuctionPlatform.services.bidden_price;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.onlineAuctionPlatform.entities.BiddenPrice;
import com.example.onlineAuctionPlatform.entities.Product;
import com.example.onlineAuctionPlatform.helpers.BiddenPriceHelper;
import com.example.onlineAuctionPlatform.services.bidder.BidderRepository;
import com.example.onlineAuctionPlatform.services.product.ProductRepository;

@Service
public class BiddenPriceServiceImpl implements BiddenPriceService {

    private BiddenPriceRepository biddenPriceRepo;
    private ProductRepository productRepo;
    private BidderRepository bidderRepo;

    public BiddenPriceServiceImpl(
        BiddenPriceRepository biddenPriceRepo,
        ProductRepository productRepo,
        BidderRepository bidderRepo
    ) {
        this.biddenPriceRepo = biddenPriceRepo;
        this.productRepo = productRepo;
        this.bidderRepo = bidderRepo;
    }

    @Override
    public List<BiddenPrice> getBiddenPriceByProductId(int productId) {
        return new ArrayList<>();
    }

    @Override
    public List<BiddenPrice> getBiddenPriceByBidderId(int bidderId) {
        return new ArrayList<>();
    }

    @Override
    public BiddenPrice add(BiddenPrice biddenPrice) {
        // check if productId exist
        if (productRepo.findById(biddenPrice.getProductId()).isEmpty()) {
            throw new RuntimeException("No such product id : " + biddenPrice.getProductId());
        }

        // check if bidderId exist
        if (bidderRepo.findById(biddenPrice.getBidderId()).isEmpty()) {
            throw new RuntimeException("No such bidder id : " + biddenPrice.getBidderId());
        }

        // check whether biddenPrice is higher than product's current price
        Product product = productRepo.findById(biddenPrice.getProductId()).get();
        int currentPrice = product.getCurrentPrice();
        int minPrice = (int) (currentPrice * 1.05);
        if (biddenPrice.getPrice() < minPrice) {
            throw new RuntimeException("The price is too low, smallest value : " + minPrice);
        }

        // bidden price is valid, save the current price of the product
        product.setCurrentPrice(biddenPrice.getPrice());
        productRepo.save(product);
        
        return biddenPriceRepo.save(biddenPrice);
    }
    
}
