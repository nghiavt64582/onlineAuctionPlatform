package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int auctioneerId;
    private String imageUrl;
    private LocalDateTime postedDate;
    private int beginningPrice;
    private int currentPrice;
    private int state;

    public Product() {
    }

    public Product(int auctioneerId, String imageUrl, LocalDateTime postedDate, int beginningPrice) {
        this.auctioneerId = auctioneerId;
        this.imageUrl = imageUrl;
        this.postedDate = LocalDateTime.now();
        this.beginningPrice = beginningPrice;
        this.currentPrice = beginningPrice;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getAuctioneerId() {
        return auctioneerId;
    }
    
    public void setAuctioneerId(int auctioneerId) {
        this.auctioneerId = auctioneerId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate() {
        postedDate = LocalDateTime.now();
    }

    public int getBeginningPrice() {
        return beginningPrice;
    }

    public void setBeginningPrice(int beginningPrice) {
        this.beginningPrice = beginningPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
