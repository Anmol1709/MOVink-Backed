package com.movink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movink.demo.Entity.Movink_user;
import com.movink.demo.Reposetory.userReposetory;


@Service
public class userServices {

     @Autowired
    private userReposetory userRepository;

    public Movink_user registerUser(String username, String password) {
       
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

    
        Movink_user newUser = new Movink_user();
        newUser.setUsername(username);
        newUser.setPassword(password);

        
        return userRepository.save(newUser);
    }
}
