package com.dapo.auth.dto;

import com.dapo.common.authentication.StandardUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

public class StandardUserDto implements StandardUser {

    public StandardUserDto(Object o) {

    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getLang() {
        return null;
    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setPhone(String phone) {

    }

    @Override
    public void setLang(String lang) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public boolean hasAuthority(GrantedAuthority authority) {
        return false;
    }

    @Override
    public Map<String, Object> getDetails() {
        return null;
    }

    @Override
    public void eraseCredentials() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
