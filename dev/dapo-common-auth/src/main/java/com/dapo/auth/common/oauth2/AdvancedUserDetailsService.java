package com.dapo.auth.common.oauth2;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by dimomass on 22.01.19.
 */
public interface AdvancedUserDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String email);
    UserDetails loadUserById(Long id);
}
