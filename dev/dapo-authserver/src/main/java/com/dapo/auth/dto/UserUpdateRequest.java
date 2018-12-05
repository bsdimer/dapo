package com.dapo.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Created by dimer on 21.03.17.
 */
public class UserUpdateRequest {

    @NotNull
    private String username;

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String phone;

    private String lang;

    private Boolean termsagreed;

    public UserUpdateRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getTermsagreed() {
        return termsagreed;
    }

    public void setTermsagreed(Boolean termsagreed) {
        this.termsagreed = termsagreed;
    }
}
