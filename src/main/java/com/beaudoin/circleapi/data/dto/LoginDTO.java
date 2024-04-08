package com.beaudoin.circleapi.data.dto;

import org.springframework.stereotype.Component;


@Component
public class LoginDTO {
    public LoginDTO() {}

    public LoginDTO(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    private String userEmail;
    private String userPassword;
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    
}
