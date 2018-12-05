package com.dapo.auth.authentication;

import com.dapo.auth.exceptions.OperationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by dimer on 17.10.16.
 */
public class MultiAuthenticationUserDetailService implements UserDetailsService {

    private AuthenticationManager authenticationManager;

    public MultiAuthenticationUserDetailService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        if (authenticationManager instanceof MultiAuthenticationManager) {
            MultiAuthenticationManager multiAuthenticationManager = (MultiAuthenticationManager) authenticationManager;
            return multiAuthenticationManager.getSessions().get(name);
        }
        throw new OperationException("MultiAuthenticationUserDetailService should be bound with MultiAuthenticationManager");
    }
}
