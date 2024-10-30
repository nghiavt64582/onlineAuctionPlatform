package com.example.onlineAuctionPlatform.helpers;

import com.example.onlineAuctionPlatform.entities.User;

public class UserHelper {
    
    public static String updateUserPasswordEncrypt(String password) {
        if (password.startsWith("{noop}")) {
            return password;
        } else {
            return "{noop}" + password;
        }
    }

    public static boolean updateUserPasswordEncrypt(User user) {
        String newPassword = updateUserPasswordEncrypt(user.getPassword());
        if (newPassword.equals(user.getPassword())) {
            return false;
        } else {
            user.setPassword(newPassword);
            return true;
        }
    }

}
