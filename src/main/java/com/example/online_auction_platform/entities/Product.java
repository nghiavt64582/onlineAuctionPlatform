package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imageUrl;
    private int beginningPrice;
    private int currentPrice;

    @Column(name = "state")
    private String state;
    
    @Column(name = "location")
    private String location;

    @Column(name = "name")
    private String name;
    
    @Column(name = "sold_date")
    private LocalDateTime soldDate;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auctioneer_id")
    @JsonIgnore
    private Auctioneer auctioneer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BiddenPrice> biddenPrices;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", beginningPrice=" + beginningPrice +
                ", currentPrice=" + currentPrice +
                ", state=" + state +
                ", auctioneer=" + auctioneer.getId() +
                ", biddenPrices=" + biddenPrices.stream().map((e) -> {return e.getId();}).collect(Collectors.toList()) +
                '}';
    }
}
