package com.example.online_auction_platform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_auction_platform.entities.Authority;
import com.example.online_auction_platform.entities.User;
import com.example.online_auction_platform.enums.Role;
import com.example.online_auction_platform.helpers.UserHelper;
import com.example.online_auction_platform.services.auctioneer.AuctioneerService;
import com.example.online_auction_platform.services.authority.AuthorityService;
import com.example.online_auction_platform.services.bidder.BidderService;
import com.example.online_auction_platform.services.user.UserService;
// import com.google.gson.Gson;
import com.google.gson.Gson;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private AuthorityService authorityService;
    private Gson gson = new Gson();
    
    public AdminController(
        UserService userService,
        AuthorityService authorityService
    ) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    // get all users
    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> result = userService.getAllUsers();
        return result;
    }

    // get an user
    @GetMapping("/users/{username}")
    public User getUser(@RequestBody String username) {
        User user = userService.findByUsername(username);
        return user;
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
        if  (userService.findByUsername(authority.getUsername()) == null) {
            throw new RuntimeException("No such username");
        }
        System.out.println("Save authority : " + gson.toJson(authority).toString());
        Authority dbAuth = authorityService.save(authority);
        System.out.println("Saved successfully : " + gson.toJson(authority).toString());
        return dbAuth;
    }

}
