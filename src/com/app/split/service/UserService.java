package com.app.split.service;

import com.app.split.entities.Split;
import com.app.split.entities.User;

import java.util.HashMap;
import java.util.Map;

//add user
//remove user
public class UserService {

    static Map<String, User> availableUsers = new HashMap<>();

    public void addUser(String userId, String name, String email, String contact) {
        User user = new User(userId, name, email, contact);
        availableUsers.put(userId, user);
    }

    public void removeUser(String userId) {
        if (!availableUsers.containsKey(userId)) {
            System.out.println("user: " + userId + " does not exists");
        }
        availableUsers.remove(userId);
        System.out.println("successfully removed user : " + userId);
    }

    public boolean isValidUserIds(Map<String, Double> userToExpenseMap) {
        for(Map.Entry<String, Double> entry : userToExpenseMap.entrySet()){
            if(!availableUsers.containsKey(entry.getKey()))
                return false;
        }
        return true;
    }

}
