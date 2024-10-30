package com.example.onlineAuctionPlatform.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bidder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int name;
    private int email;
    private int cash;
    private String username;
    private LocalDateTime createdDate;

    public Bidder() {
    }

    public Bidder(
        int id, 
        int name, 
        int email, 
        int cash,
        String username,
        int createdDate
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cash = cash;
        this.username = username;
        this.createdDate = LocalDateTime.now();
    }

    public Bidder(String username) {
        this.username = username;
        this.cash = 0;
        this.createdDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getName() {
        return name;
    }
    
    public void setName(int name) {
        this.name = name;
    }
    
    public int getEmail() {
        return email;
    }
    
    public void setEmail(int email) {
        this.email = email;
    }
    
    public int getCash() {
        return cash;
    }
    
    public void setCash(int cash) {
        this.cash = cash;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
   
}
