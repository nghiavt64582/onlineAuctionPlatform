package com.example.online_auction_platform.entities;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "user")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    
    @Id
    @Size(max = 25, min = 5)
    @Pattern(regexp = "^[a-zA-Z0-9.]+$")
    @Column(name = "id")
    protected int id;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    @Column(name = "avatar")
    protected String avatar;

    @Column(name = "role")
    protected String role;

    @Column(name = "enabled")
    protected int enabled;

    @Column(name = "email")
    protected String email;

    @Column(name = "created_date")
    protected LocalDateTime createdDate;

    @Column(name = "last_login")
    protected LocalDateTime lastLogin;

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public int getEnabled() {
        return enabled;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
