package com.dapo.auth.dto;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by dimomass on 02.10.18.
 */
public class AuthorityUpdateRequest {
    @NotNull
    private String username;
    @NotNull
    private String scope;
    @NotNull
    private Set<String> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
