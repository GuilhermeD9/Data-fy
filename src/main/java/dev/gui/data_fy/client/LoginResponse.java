package dev.gui.data_fy.client;

public class LoginResponse {
    private String accessToken;

    //Construtores, getters e setters
    public LoginResponse() {
    }

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
