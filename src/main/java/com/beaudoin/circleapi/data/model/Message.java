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

    @ManyToOne
    @JoinColumn(name = "MESSAGE_RECEIVER")
    private User messageReceiver;

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

    public User getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(User messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public long getMessageSenderId() {
        return messageSenderId;
    }

    public void setMessageSenderId(long messageSenderId) {
        this.messageSenderId = messageSenderId;
    }
}
