package com.dapo.auth.common.oauth2;

/**
 * Created by dimomass on 22.01.19.
 */
public interface User {

    Long getId();

    String getName();

    String getEmail();

    String getImageUrl();

    Boolean getEmailVerified();

    String getPassword();

    AuthProvider getProvider();

    String getProviderId();

}
