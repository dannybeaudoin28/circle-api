package com.beaudoin.circleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List userList = userService.findAllUsers();
        if (!userList.isEmpty())
            return new ResponseEntity<>(userList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/get-user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        if (id > 0) {
            User user = userService.getUserById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/post-user", method = RequestMethod.POST)
    public ResponseEntity<Integer> postUser(@RequestBody User user) {
        boolean responseCode = false;

        if (user != null) {
            responseCode = userService.postUser(user);
            if (responseCode)
                return new ResponseEntity<Integer>(200, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(422, HttpStatus.BAD_REQUEST);
    }
}
