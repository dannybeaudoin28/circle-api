package com.beaudoin.circleapi.data.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class User {

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userEmail=" + userEmail + ", userFName=" + userFName + ", userLName="
                + userLName + ", userMiddleInitial=" + userMiddleInitial + ", userPassword=" + userPassword
                + ", userImage=" + Arrays.toString(userImage) + "]";
    }

    public User() {}

    public User(String userEmail, String userFName, String userLName, char userMiddleInitial, String userPassword) {
        this.userEmail = userEmail;
        this.userFName = userFName;
        this.userLName = userLName;
        this.userMiddleInitial = userMiddleInitial;
        this.userPassword = userPassword;
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
}