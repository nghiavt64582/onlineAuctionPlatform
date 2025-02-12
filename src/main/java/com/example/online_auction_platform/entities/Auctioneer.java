package com.example.online_auction_platform.entities;


import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auctioneer")
public class Auctioneer extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cash")
    private int cash;

    @OneToMany(mappedBy = "auctioneer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public void updateCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

}
