package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sold_product")
public class SoldProduct extends Product {
    
    @ManyToOne()
    @JoinColumn(name = "bidder_id")
    private Bidder bidder;
    
    @Column(name = "sold_date")
    private LocalDateTime soldDate;
    

}
