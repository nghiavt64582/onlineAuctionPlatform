package com.example.onlineAuctionPlatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineAuctionPlatform.entities.Authority;
import com.example.onlineAuctionPlatform.entities.User;
import com.example.onlineAuctionPlatform.enums.Role;
import com.example.onlineAuctionPlatform.helpers.UserHelper;
import com.example.onlineAuctionPlatform.services.auctioneer.AuctioneerService;
import com.example.onlineAuctionPlatform.services.authority.AuthorityService;
import com.example.onlineAuctionPlatform.services.bidder.BidderService;
import com.example.onlineAuctionPlatform.services.user.UserService;
// import com.google.gson.Gson;
import com.google.gson.Gson;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private AuthorityService authorityService;
    private AuctioneerService auctioneerService;
    private BidderService bidderService;
    private Gson gson = new Gson();
    
    public AdminController(
        UserService userService, 
        AuthorityService authorityService,
        AuctioneerService auctioneerService,
        BidderService bidderService
    ) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.auctioneerService = auctioneerService;
        this.bidderService = bidderService;
    }

    // get all users
    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> result = userService.getAllUsers();
        return result;
    }

    // add a new user
    @PostMapping("/users")
    public User add(@RequestBody User user) {
        boolean changePassword = UserHelper.updateUserPasswordEncrypt(user);
        System.out.println("Update password for user : " + changePassword);
        User dbStudent = userService.save(user);
        return dbStudent;
    }

    // add a new authority
    @PostMapping("/authorities")
    public Authority setAuthority(@RequestBody Authority authority) {
        if  (userService.getByUsername(authority.getUsername()) == null) {
            throw new RuntimeException("No such username");
        }
        if (authority.getAuthority().endsWith(Role.AUCTIONEER.toString())) {
            auctioneerService.addAuctioneerByUsername(authority.getUsername());
        }
        if (authority.getAuthority().endsWith(Role.BIDDER.toString())) {
            bidderService.addBidderByUsername(authority.getUsername());
        };
        System.out.println("Save authority : " + gson.toJson(authority).toString());
        Authority dbAuth = authorityService.save(authority);
        System.out.println("Saved successfully : " + gson.toJson(authority).toString());
        return dbAuth;
    }

}
