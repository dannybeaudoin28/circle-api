package com.beaudoin.circleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beaudoin.circleapi.data.model.User;
import com.beaudoin.circleapi.data.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean postUser(User user) {
        if (user != null) {
            try {
                userRepo.save(user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}