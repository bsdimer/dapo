package com.dapo.auth.dto;


import javax.validation.constraints.NotEmpty;

/**
 * Created by dimer on 21.03.17.
 */
public class PasswordRecoveryRequest {

    @NotEmpty
    private String token;

    @NotEmpty
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
