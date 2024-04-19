package com.beaudoin.circleapi.responses;

public class TokenResponse {
    private String token;
    private long id;

    public TokenResponse(String token, long id) {
        this.token = token;
        this.id = id;
    }
}
