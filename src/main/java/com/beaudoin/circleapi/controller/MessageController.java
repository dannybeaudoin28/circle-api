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
import org.springframework.web.bind.annotation.RestController;

import com.beaudoin.circleapi.data.model.Message;
import com.beaudoin.circleapi.data.model.User;
import com.beaudoin.circleapi.service.MessageService;
import com.beaudoin.circleapi.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) { 
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping(value = "/post-message", method = RequestMethod.POST)
    public ResponseEntity<Integer> postMessage(@RequestBody Message message, HttpServletRequest request) {
        System.out.println("inside post-message");
        boolean responseCode = false;

        if (message != null) {
            messageService.sendMessage(message.getMessageSenderId(), message.getMessageReceiver(), message.getMessageBody());

            System.out.println("id is: " + message.getMessageId());

            User user = userService.getUserById(message.getMessageSenderId());

            if (user != null) {
                user.getMessages().add(message);
                userService.updateUser(user);
            }
            return new ResponseEntity<>(422, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(422, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/get-messages/{userId1}/{userId2}", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable long userId1, @PathVariable long userId2) {
        List messageList = (List) messageService.findAllMessages(userId1, userId2);
        if (!messageList.isEmpty())
            return new ResponseEntity<>(messageList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
