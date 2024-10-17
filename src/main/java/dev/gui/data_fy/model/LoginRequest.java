package dev.gui.data_fy.model;

import feign.form.FormProperty;

public class LoginRequest {
    //Dados solicitados para Login
    @FormProperty("grant_type")
    private String grantType;
    @FormProperty("client_id")
    private String clientID;
    @FormProperty("client_secret")
    private String clientSecret;
    @FormProperty("code")
    private String code;
    @FormProperty("redirect_uri")
    private String redirectUri;

    //Construtores, Getters e Setters
    //Construtor para o fluxo de client_credentials
    public LoginRequest(String grantType, String clientID, String clientSecret) {
        this.grantType = grantType;
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    //Construtor para o fluxo de authorization_code
    public LoginRequest(String grantType, String clientID, String clientSecret, String code, String redirectUri) {
        this.grantType = grantType;
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.code = code;
        this.redirectUri = redirectUri;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
