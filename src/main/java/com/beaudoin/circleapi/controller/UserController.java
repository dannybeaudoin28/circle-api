package com.beaudoin.circleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beaudoin.circleapi.data.model.User;
import com.beaudoin.circleapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/post-user", method = RequestMethod.POST)
    public ResponseEntity<Integer> postUser(@RequestBody User user) {
        boolean responseCode = false;

        if (user != null) {
            responseCode = userService.postUser(user);
            if (responseCode)
                return new ResponseEntity<Integer>(200, HttpStatus.OK);
        }
        return new ResponseEntity<>(422, HttpStatus.BAD_REQUEST);
    }
}
