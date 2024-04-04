package com.beaudoin.circleapi.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beaudoin.circleapi.data.model.Message;
import com.beaudoin.circleapi.data.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepo;

    @Autowired
    public MessageService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public boolean saveMessage(Message message) {
        if (message != null) {
            try {
                messageRepo.save(message);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List findAllMessages() {
        return (List) messageRepo.findAll();
    }
}
