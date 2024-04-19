package com.beaudoin.circleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.beaudoin.circleapi.data.model.Message;
import com.beaudoin.circleapi.data.model.User;
import com.beaudoin.circleapi.data.repository.MessageRepository;
import com.beaudoin.circleapi.data.repository.UserRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepo;

    public void sendMessage(long senderId, User receiver, String text) {
        Message message = new Message();
        message.setMessageSenderId(senderId);
        message.setMessageReceiver(receiver);
        message.setMessageBody(text);

        messageRepo.save(message);
    }

    public List findAllMessages(long id1, long id2) {
        return (List) messageRepo.findMessagesBetweenUsers(id1, id2);
    }
}
