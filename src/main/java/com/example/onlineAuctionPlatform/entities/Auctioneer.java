package com.example.onlineAuctionPlatform.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auctioneer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String name;
    private String email;
    private int cash;
    private LocalDateTime createdDate;

    public Auctioneer(String username) {
        this.username = username;
        this.cash = 0;
        this.createdDate = LocalDateTime.now();
    }

    public Auctioneer() {
    }

    public Auctioneer(String name, String email, int cash, LocalDateTime createdDate) {
        this.name = name;
        this.email = email;
        this.cash = cash;
        this.createdDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getCash() {
        return cash;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

}
