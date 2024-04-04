package com.beaudoin.circleapi.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class User {
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
}
