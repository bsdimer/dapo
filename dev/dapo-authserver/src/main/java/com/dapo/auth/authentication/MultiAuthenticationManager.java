package com.dapo.auth.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dimer on 17.10.16.
 */
public class MultiAuthenticationManager implements AuthenticationManager {

    private List<AuthenticationProvider> providers = new LinkedList<>();
    private Map<String, UserDetails> sessions = new ConcurrentHashMap<>();

    public MultiAuthenticationManager() {
    }

    public MultiAuthenticationManager(List<AuthenticationProvider> providers) {
        this.providers = providers;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication currentAuthentication = null;
        for (AuthenticationProvider provider : providers) {
            try {
                currentAuthentication = provider.authenticate(authentication);
            } catch (AuthenticationException ex) {
                continue;
            }
            if (currentAuthentication.isAuthenticated()) {
                sessions.put(authentication.getName(), (UserDetails) currentAuthentication.getPrincipal());
                SecurityContextHolder.getContext().setAuthentication(currentAuthentication);
                return currentAuthentication;
            }
        }
        throw new BadCredentialsException("user_not_found_or_password_incorrect");
    }

    public MultiAuthenticationManager addProvider(AuthenticationProvider provider) {
        providers.add(provider);
        return this;
    }

    public Map<String, UserDetails> getSessions() {
        return sessions;
    }
}
