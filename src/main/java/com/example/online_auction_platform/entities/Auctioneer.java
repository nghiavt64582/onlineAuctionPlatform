package com.example.online_auction_platform.entities;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auctioneer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String name;
    private String email;
    private int cash;

    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") 
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime createdDate;

    @OneToMany(mappedBy = "auctioneer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public Auctioneer(String username) {
        this.username = username;
        this.cash = 0;
        this.createdDate = LocalDateTime.now();
    }

    public Auctioneer(String name, String email, int cash, LocalDateTime createdDate) {
        this.name = name;
        this.email = email;
        this.cash = cash;
        this.createdDate = LocalDateTime.now();
    }

    public void updateCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

}
