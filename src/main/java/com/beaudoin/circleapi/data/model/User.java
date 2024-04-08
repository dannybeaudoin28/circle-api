package com.beaudoin.circleapi.data.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class User {
    
    public User() {}

    public User(String userEmail, String userFName, String userLName, char userMiddleInitial, String userPassword,
            byte[] userImage, String userRole) {
        this.userEmail = userEmail;
        this.userFName = userFName;
        this.userLName = userLName;
        this.userMiddleInitial = userMiddleInitial;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.userRole = userRole;
    }

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_F_NAME")
    private String userFName;

    @Column(name = "USER_L_NAME")
    private String userLName;

    @Column(name = "USER_M_INITIAL")
    private char userMiddleInitial;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_IMG")
    private byte[] userImage;

    @Column(name = "USER_ROLE")
    private String userRole;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFName() {
        return userFName;
    }

    public void setUserFName(String userFName) {
        this.userFName = userFName;
    }

    public String getUserLName() {
        return userLName;
    }

    public void setUserLName(String userLName) {
        this.userLName = userLName;
    }

    public char getUserMiddleInitial() {
        return userMiddleInitial;
    }

    public void setUserMiddleInitial(char userMiddleInitial) {
        this.userMiddleInitial = userMiddleInitial;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    
}