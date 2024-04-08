package com.beaudoin.circleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beaudoin.circleapi.data.dto.LoginDTO;
import com.beaudoin.circleapi.service.CustomUserDetailsService;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginDTO login) {
    System.out.println("Logging User details: UserEmail: " );
        UserDetails user = customUserDetailsService.loadUserByUsername(login.getUserEmail());

        //TODO : currently when the user has a valid email and pw it works as intended. if just invalid email error occurs
        // Check if the provided password matches the hashed password from the database
        if (bCryptPasswordEncoder.matches(login.getUserPassword(), user.getPassword())) {
            String jwtToken = JwtUtil.generateToken(user.getUsername(), user.getPassword());
            System.out.println("Token: " + jwtToken);
            return jwtToken;
        } else {
            return "Incorrect login";
        }
    }

}
