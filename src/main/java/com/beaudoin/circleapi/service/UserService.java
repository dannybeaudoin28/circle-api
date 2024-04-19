package com.beaudoin.circleapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.beaudoin.circleapi.data.model.User;
import com.beaudoin.circleapi.data.repository.UserRepository;

@Service
public class UserService {

    @Bean
    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean saveUser(User user) {
        if (user != null) {
            try {
                String encryptedPw = bCryptPasswordEncoder().encode(user.getUserPassword());
                user.setUserPassword(encryptedPw);
                userRepo.save(user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void updateUser(User user) {
        if (user != null) {
            userRepo.save(user);
        }
    }

    public User getUserById(long id) {
        return userRepo.findById(id).get();
    }

    public User getUserByEmail(String email) {
        User user = userRepo.findByUserEmail(email);
        System.out.println(user.toString());
        return user;
    }

    public List<User> getUsersByEmail(String email) {
        List<User> userList = userRepo.findAllByUserEmail(email);
        return userList;
    }

    public List findAllUsers() {
        return (List) userRepo.findAll();
    }

    public boolean deleteUserById(long id) {
        userRepo.deleteById(id);
        return true;
    }

}
