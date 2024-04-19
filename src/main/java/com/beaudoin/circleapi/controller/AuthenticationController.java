package com.beaudoin.circleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beaudoin.circleapi.data.dto.LoginDTO;
import com.beaudoin.circleapi.responses.TokenResponse;
import com.beaudoin.circleapi.service.CustomUserDetailsService;
import com.beaudoin.circleapi.service.UserService;
import com.beaudoin.circleapi.utility.JwtUtil;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginDTO login) {
        UserDetails user = customUserDetailsService.loadUserByUsername(login.getUserEmail());

        System.out.println("password is: " + login.getUserPassword());

        if (bCryptPasswordEncoder.matches(login.getUserPassword(), user.getPassword())) {
            return JwtUtil.generateToken(user.getUsername(), user.getPassword(), user.getAuthorities()); 
        } else {
            return "Incorrect login";
        }
    }

    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // public ResponseEntity<?> login(@RequestBody LoginDTO login) {
    //     UserDetails user = customUserDetailsService.loadUserByUsername(login.getUserEmail());

    //     long userId = userService.getUserByEmail(login.getUserEmail()).getUserId();

    //     if (bCryptPasswordEncoder.matches(login.getUserPassword(), user.getPassword())) {
    //         String token = JwtUtil.generateToken(user.getUsername(), user.getPassword(), user.getAuthorities()); 
    //         TokenResponse tokenResponse = new TokenResponse(token, userId);
    //         return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(null);
    //     }
    // }
}
