package com.example.onlineAuctionPlatform.services.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.onlineAuctionPlatform.entities.User;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getByUsername(String username) {
        Optional<User> result = userRepo.findById(username);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
    
}
