package com.example.onlineAuctionPlatform.entities;

import org.joda.time.DateTime;

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
    private DateTime createdDate;

    public Bidder(int id, int name, int email, int cash, int createdDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cash = cash;
        this.createdDate = DateTime.now();
    }

    public int getId() {
        return id;
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
    
    public DateTime getCreatedDate() {
        return createdDate;
    }
   
}
