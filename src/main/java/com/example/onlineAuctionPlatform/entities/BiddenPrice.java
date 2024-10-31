package com.example.onlineAuctionPlatform.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BiddenPrice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bidderId;
    private int price;
    private int productId;
    private LocalDateTime createdDate;
    
    public BiddenPrice() {
    }

    public BiddenPrice(int bidderId, int price, int productId) {
        this.bidderId = bidderId;
        this.price = price;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public int getBidderId() {
        return bidderId;
    }

    public int getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    
}
