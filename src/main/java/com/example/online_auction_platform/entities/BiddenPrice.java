package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BiddenPrice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private Bidder bidder;

    private int price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime createdDate;
    
    public BiddenPrice() {
    }

    public BiddenPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public int getBidderId() {
        return bidder.getId();
    }

    public int getProductId() {
        return product.getId();
    }

    public void updateCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }
    
}
