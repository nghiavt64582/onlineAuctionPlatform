package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@Table(name = "bidder")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bidder extends User {
    
    @Id
    private int id;

    @Column(name = "cash")
    private int cash;

    @Column(name = "n_bought_product")
    private int nBoughtProducts;

    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SoldProduct> boughtProducts;
   
    public void updateCreatedDate() {
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }

}
