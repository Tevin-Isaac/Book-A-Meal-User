package com.jamescliff.mealbooking.Api;

public class LoginResponse {

    private String authToken;


    public LoginResponse(String authToken) {
        this.authToken = authToken;
    }


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
