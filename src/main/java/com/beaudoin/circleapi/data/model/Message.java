package com.beaudoin.circleapi.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "MESSAGE_TABLE")
public class Message {

    public Message () {}

    public Message(String messageBody, Date messageSentDate) {
        this.messageBody = messageBody;
        this.messageSentDate = messageSentDate;
        this.messageDeleted = false;
    }

    @Id
    @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private long messageId;

    @Column(name = "MESSAGE_BODY")
    private String messageBody;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "MESSAGE_SENT_DATE")
    private Date messageSentDate;

    @Column(name = "MESSAGE_DELETED")
    private boolean messageDeleted;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "MESSAGE_DELETED_DATE")
    private Date messageDeletedDate;

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

    public Date getMessageSentDate() {
        return messageSentDate;
    }

    public void setMessageSentDate(Date messageSentDate) {
        this.messageSentDate = messageSentDate;
    }

    public boolean isMessageDeleted() {
        return messageDeleted;
    }

    public void setMessageDeleted(boolean messageDeleted) {
        this.messageDeleted = messageDeleted;
    }

    public Date getMessageDeletedDate() {
        return messageDeletedDate;
    }

    public void setMessageDeletedDate(Date messageDeletedDate) {
        this.messageDeletedDate = messageDeletedDate;
    }   
}