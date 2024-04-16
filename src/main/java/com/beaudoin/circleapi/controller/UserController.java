package com.beaudoin.circleapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beaudoin.circleapi.data.model.User;
import com.beaudoin.circleapi.service.UserService;
import com.beaudoin.circleapi.utility.JwtUtil;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
        //TODO: create method inside JwtUtil to extract the roles, return them.
        //      compare role to desired scope. ie: a user requires admin role for this endpoint 
        //      if(JwtUtil.validateJwtToken(request) && userRole == "ROLE_ADMIN")
        String bearerToken = request.getHeader("Authorization").substring(7);
        List<String> roles = JwtUtil.extractAuthorities(bearerToken);

        if (JwtUtil.validateJwtToken(request) && roles.contains("ROLE_ADMIN")) {
            List<User> userList = userService.findAllUsers();

            for (User user : userList) {
                System.out.println(user.toString());
            }

            if (!userList.isEmpty())
                return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/get-user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable long id, HttpServletRequest request) {
        if (id > 0) {
            User user = userService.getUserById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/post-user", method = RequestMethod.POST)
    public ResponseEntity<Integer> postUser(@RequestBody User user, HttpServletRequest request) {
        System.out.println("Inside post user");
        boolean responseCode = false;

        if (user != null) {
            responseCode = userService.saveUser(user);
            if (responseCode)
                return new ResponseEntity<Integer>(200, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(422, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update-user", method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateUser(@RequestBody User updatedUser, HttpServletRequest request) {
        System.out.println(updatedUser.toString());
        User oldUser = userService.getUserById(updatedUser.getUserId());

        if (oldUser != null) {
            oldUser.setUserEmail(updatedUser.getUserEmail());
            oldUser.setUserFName(updatedUser.getUserFName());
            oldUser.setUserLName(updatedUser.getUserLName());
            oldUser.setUserMiddleInitial(updatedUser.getUserMiddleInitial());
            oldUser.setUserImage(updatedUser.getUserImage());
            oldUser.setUserPassword(updatedUser.getUserPassword());

            boolean userUpdated = userService.saveUser(oldUser);

            if (userUpdated) {
                return new ResponseEntity<>(200, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteUser(@PathVariable long id, HttpServletRequest request) {
        User userToDelete = userService.getUserById(id);
        if (userToDelete != null) {
            boolean userDeleted = userService.deleteUserById(id);
            if (userDeleted)
                return new ResponseEntity<>(200, HttpStatus.OK);
        }
        return new ResponseEntity<>(200, HttpStatus.BAD_REQUEST);
    }

}
