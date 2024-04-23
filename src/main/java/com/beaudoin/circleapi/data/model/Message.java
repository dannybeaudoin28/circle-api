package com.beaudoin.circleapi.data.model;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE_TABLE")
public class Message {

    public Message() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private long messageId;

    @Column(name = "MESSAGE_BODY", nullable = false)
    private String messageBody;

    @Column(name = "MESSAGE_SENDER_ID")
    private long messageSenderId;

    @Column(name = "MESSAGE_RECEIVER_ID")
    private long messageReceiverId;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public long getMessageSenderId() {
        return messageSenderId;
    }

    public void setMessageSenderId(long messageSenderId) {
        this.messageSenderId = messageSenderId;
    }

    public long getMessageReceiverId() {
        return messageReceiverId;
    }

    public void setMessageReceiverId(long messageReceiverId) {
        this.messageReceiverId = messageReceiverId;
    }

    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", messageBody=" + messageBody + ", messageSenderId="
                + messageSenderId + ", messageReceiverId=" + messageReceiverId + "]";
    }

    
}
