package dev.gui.data_fy.model;

import feign.form.FormProperty;

public class LoginRequest {
    //Dados solicitados para Login
    @FormProperty("grant_type")
    private String grantType;
    @FormProperty("client_id")
    private String client_id;
    @FormProperty("client_secret")
    private String client_secret;
    @FormProperty("code")
    private String code;
    @FormProperty("redirect_uri")
    private String redirect_uri;

    //Construtores, Getters e Setters
    //Construtor para o fluxo de client_credentials
    public LoginRequest(String grantType, String client_id, String client_secret) {
        this.grantType = grantType;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    //Construtor para o fluxo de authorization_code
    public LoginRequest(String grantType, String client_id, String client_secret, String code, String redirect_uri) {
        this.grantType = grantType;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = redirect_uri;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }
}
