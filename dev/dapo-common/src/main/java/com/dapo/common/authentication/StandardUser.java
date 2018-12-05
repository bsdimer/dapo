package com.dapo.common.authentication;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

/**
 * Created by dimomass on 24.11.18.
 */
public interface StandardUser extends UserDetails, CredentialsContainer {

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhone();

    String getLang();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setEmail(String email);

    void setPhone(String phone);

    void setLang(String lang);

    void setPassword(String password);

    boolean hasAuthority(GrantedAuthority authority);

    Map<String, Object> getDetails();

}
