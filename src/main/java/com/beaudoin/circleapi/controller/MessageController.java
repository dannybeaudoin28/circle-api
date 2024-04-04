package com.beaudoin.circleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beaudoin.circleapi.data.model.Message;
import com.beaudoin.circleapi.service.MessageService;

@RestController
@RequestMapping(name = "/messages")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) { 
        this.messageService = messageService;
    }

    @RequestMapping(value = "/get-messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllMessages() {
        List messageList = (List) messageService.findAllMessages();
        if (!messageList.isEmpty())
            return new ResponseEntity<>(messageList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
